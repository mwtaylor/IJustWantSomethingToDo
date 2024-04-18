package app.elephantintheroom.somethingtodo.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.ThingToDoRepository
import app.elephantintheroom.somethingtodo.data.TimeSpent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant

class ThingsToDoViewModel(val thingToDoRepository: ThingToDoRepository) : ViewModel() {
    private val uiStateStream: Flow<ThingsToDoUiState> =
        thingToDoRepository.getAllThingsToDoStream()
            .flatMapLatest {
                it.fold(listOf(ThingsToDoUiState()).asFlow()) { uiStateFlow, thingToDo ->
                    uiStateFlow.combine(
                        thingToDoRepository.getActiveThingToDoStream(thingToDo)
                    ) { uiState, timeSpent ->
                        Log.i("ThingsToDoViewModel", thingToDo.name)
                        ThingsToDoUiState(uiState.thingsToDo.plus(thingToDo to timeSpent))
                    }
                }
            }
            .combine(thingToDoRepository.getActiveThingToDoStream()) { uiState, activeThingToDo ->
                ThingsToDoUiState(uiState.thingsToDo, activeThingToDo)
            }

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

data class ThingsToDoUiState(
    val thingsToDo: Map<ThingToDo, TimeSpent?> = mapOf(),
    val activeThingToDo: Pair<ThingToDo, TimeSpent>? = null,
)
