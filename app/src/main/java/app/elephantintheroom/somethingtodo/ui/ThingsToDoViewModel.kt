package app.elephantintheroom.somethingtodo.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.ThingToDoRepository
import app.elephantintheroom.somethingtodo.data.TimeSpent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.MonthDay
import java.time.Year
import java.time.YearMonth
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.WeekFields
import java.util.Calendar

class ThingsToDoViewModel(private val thingToDoRepository: ThingToDoRepository) : ViewModel() {
    @OptIn(ExperimentalCoroutinesApi::class)

    fun Flow<ThingsToDoRawData>.addActiveThingToDo(): Flow<ThingsToDoRawData> {
        return this.combine(thingToDoRepository.getActiveThingToDoStream()) { thingsToDoRawData, activeThingToDo ->
            thingsToDoRawData.copy(activeThingToDo = activeThingToDo)
        }
    }

    fun Flow<ThingsToDoRawData>.addAllThingsToDo(): Flow<ThingsToDoRawData> {
        return this.combine(thingToDoRepository.getAllThingsToDoStream()) { thingsToDoRawData, thingsToDo ->
            thingsToDoRawData.copy(thingsToDoRawData = thingsToDo)
        }
    }

    fun truncated(instant: Instant, chronoUnit: ChronoUnit): Instant {
        return instant.truncatedTo(chronoUnit)
    }

    fun startOfWeek(instant: Instant): Instant {
        return LocalDate.from(instant.atZone(ZoneId.systemDefault())).with(WeekFields.SUNDAY_START.firstDayOfWeek).atStartOfDay(ZoneId.systemDefault()).toInstant()
    }

    fun startOfMonth(instant: Instant): Instant {
        return YearMonth.from(instant.atZone(ZoneId.systemDefault())).atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant()
    }

    fun startOfYear(instant: Instant): Instant {
        return Year.from(instant.atZone(ZoneId.systemDefault())).atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant()
    }

    fun Flow<ThingsToDoRawData>.addAllRecentTimeSpent(): Flow<ThingsToDoRawData> {
        return this.flatMapLatest { thingsToDoRawData ->
            thingsToDoRawData.thingsToDoRawData.fold(listOf(mapOf<ThingToDo, ThingToDoRawData>()).asFlow()) { thingsToDoRawDataStream, thingToDo ->
                thingsToDoRawDataStream.combine(thingToDoRepository.getActiveThingToDoStream(thingToDo)) { allThingToDoRawData, activeThingToDo ->
                    allThingToDoRawData.plus(thingToDo to ThingToDoRawData(thingToDo, activeThingToDo))
                }.flatMapLatest { allThingToDoRawData ->
                    val valuesToSearchForRecentTimeSpent = mapOf<Pair<ChronoUnit, Int>, (Instant) -> Instant>(
                        Pair(ChronoUnit.DAYS, 0) to { truncated(it, ChronoUnit.DAYS) },
                        Pair(ChronoUnit.WEEKS, 0) to ::startOfWeek,
                        Pair(ChronoUnit.MONTHS, 0) to ::startOfMonth,
                        Pair(ChronoUnit.MONTHS, 1) to { it.atZone(ZoneId.systemDefault()).minus(1, ChronoUnit.MONTHS).toInstant() },
                        Pair(ChronoUnit.YEARS, 0) to ::startOfYear,
                        Pair(ChronoUnit.FOREVER, 0) to { Instant.EPOCH },
                    )
                    valuesToSearchForRecentTimeSpent.keys.fold(listOf(mapOf<Pair<ChronoUnit, Int>, Duration>()).asFlow()) { allRecentTimeSpentStream, recentTimeSpentSearch ->
                        val startSearch = valuesToSearchForRecentTimeSpent.getValue(recentTimeSpentSearch).invoke(Instant.now())

                        allRecentTimeSpentStream.combine(thingToDoRepository.getRecentTimeSpentOnThingToDo(thingToDo, startSearch)) { allRecentTimeSpent, recentTimeSpent ->
                            val totalDuration = recentTimeSpent.fold(Duration.ZERO) { acc, timeSpent ->
                                var started = timeSpent.started
                                if (started < startSearch) {
                                    started = startSearch
                                }
                                acc.plus(Duration.between(started, timeSpent.finished))
                            }
                            allRecentTimeSpent.plus(recentTimeSpentSearch to totalDuration)
                        }
                    }.map { allRecentTimeSpent ->
                        allThingToDoRawData.plus(thingToDo to allThingToDoRawData.getValue(thingToDo).copy(recentTimeSpent = allRecentTimeSpent))
                    }
                }
            }.map {
                thingsToDoRawData.copy(thingsToDo = it.values.toList())
            }
        }
    }

    fun Flow<ThingsToDoRawData>.convertToUiState(): Flow<ThingsToDoUiState> {
        return this.map { thingsToDoRawData ->
            val thingsToDo = thingsToDoRawData.thingsToDo.map { thingToDoRawData ->
                ThingToDoWithTimeSpent(thingToDoRawData.thingToDo, thingToDoRawData.activeTimeSpent, thingToDoRawData.recentTimeSpent)
            }
            ThingsToDoUiState(thingsToDo, thingsToDoRawData.activeThingToDo)
        }
    }

    val uiStateStream: Flow<ThingsToDoUiState> =
        listOf(ThingsToDoRawData()).asFlow()
            .addActiveThingToDo()
            .addAllThingsToDo()
            .addAllRecentTimeSpent()
            .convertToUiState()

    val uiState: StateFlow<ThingsToDoUiState> = uiStateStream
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ThingsToDoUiState()
        )

    fun startSpendingTime(thingToDo: ThingToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            thingToDoRepository.insertTimeSpent(
                TimeSpent(thingToDoId = thingToDo.id, started = Instant.now())
            )
        }
    }

    fun stopSpendingTime(thingToDo: ThingToDo, timeSpent: TimeSpent) {
        viewModelScope.launch(Dispatchers.IO) {
            thingToDoRepository.updateTimeSpent(timeSpent.copy(finished = Instant.now()))
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ThingToDoRawData(
    val thingToDo: ThingToDo,
    val activeTimeSpent: TimeSpent? = null,
    val recentTimeSpent: Map<Pair<ChronoUnit, Int>, Duration> = mapOf(),
)

data class ThingsToDoRawData(
    val thingsToDoRawData: List<ThingToDo> = listOf(),
    val thingsToDo: List<ThingToDoRawData> = listOf(),
    val activeThingToDo: Pair<ThingToDo, TimeSpent>? = null,
)

data class ThingToDoWithTimeSpent(
    val thingToDo: ThingToDo,
    val activeTimeSpent: TimeSpent? = null,
    val recentTimeSpent: Map<Pair<ChronoUnit, Int>, Duration> = mapOf(),
)

data class ThingsToDoUiState(
    val thingsToDo: List<ThingToDoWithTimeSpent> = listOf(),
    val activeThingToDo: Pair<ThingToDo, TimeSpent>? = null,
)
