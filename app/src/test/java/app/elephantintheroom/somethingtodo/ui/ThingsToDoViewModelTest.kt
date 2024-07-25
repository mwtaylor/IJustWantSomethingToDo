package app.elephantintheroom.somethingtodo.ui

import app.elephantintheroom.somethingtodo.TestThingToDoRepository
import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.TimeSpent
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.Clock
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

@ExperimentalCoroutinesApi
class ThingsToDoViewModelTest {
    @Test
    fun emptyRepositoryTest() {
        val viewModel = ThingsToDoViewModel(TestThingToDoRepository(listOf()), Clock.systemUTC())
        assertThat(viewModel.uiState.value.thingsToDo).isEmpty()
    }

    @Test
    fun noPreviousTimeSpentTest() {
        val viewModel = ThingsToDoViewModel(TestThingToDoRepository(listOf(ThingToDo(name = "Test"))), Clock.systemUTC())
        runTest {
            assertThat(
                viewModel.uiStateStream.first().thingsToDo.first().recentTimeSpent[Pair(ChronoUnit.DAYS, 0)]
            )
                .isEqualTo(Duration.ZERO)
        }
    }

    @Test
    fun timeSpentTodayTest() {
        val viewModel = ThingsToDoViewModel(
            TestThingToDoRepository(
                listOf(ThingToDo(id = 1, name = "Test")),
                listOf(
                    TimeSpent(
                        thingToDoId = 1,
                        started = LocalDate.now().atTime(1, 0).atZone(ZoneId.systemDefault()).toInstant(),
                        finished = LocalDate.now().atTime(5, 0).atZone(ZoneId.systemDefault()).toInstant()
                    )
                )
            ),
            Clock.fixed(LocalDate.now().atTime(8, 0).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault())
        )
        runTest {
            assertThat(
                viewModel.uiStateStream.first().thingsToDo.first().recentTimeSpent[Pair(ChronoUnit.DAYS, 0)]
            )
                .isEqualTo(Duration.ofHours(4))
        }
    }
}