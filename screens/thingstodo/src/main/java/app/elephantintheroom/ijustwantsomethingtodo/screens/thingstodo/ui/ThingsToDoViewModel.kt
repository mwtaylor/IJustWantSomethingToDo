package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elephantintheroom.ijustwantsomethingtodo.core.domain.activethingtodo.OneActiveThingToDoUseCase
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoWithTimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Clock
import java.time.Instant

class ThingsToDoViewModel(
    private val thingToDoRepository: ThingToDoRepository,
    private val timeSpentRepository: TimeSpentRepository,
    private val oneActiveThingToDoUseCase: OneActiveThingToDoUseCase,
    private val clock: Clock,
) : ViewModel() {
    val uiState: StateFlow<ThingsToDoUiState> = thingToDoRepository.getAllThingsToDoWithTimeSpent().map {
        ThingsToDoUiState(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ThingsToDoUiState(emptyList())
    )

    fun addThingToDo(thingToDo: ThingToDo, onComplete: (ThingToDoWithTimeSpent) -> Unit = {}) {
        viewModelScope.launch {
            val newThingToDo = thingToDoRepository.addThingToDo(thingToDo)
            onComplete(newThingToDo)
        }
    }

    fun startSpendingTime(thingToDo: ThingToDo) {
        viewModelScope.launch {
            oneActiveThingToDoUseCase.startThingToDo(thingToDo) { false }
        }
    }

    fun stopSpendingTime(timeSpent: TimeSpent) {
        viewModelScope.launch {
            timeSpentRepository.recordTimeSpent(timeSpent.copy(ended = Instant.now(clock)))
        }
    }
}

data class ThingsToDoUiState(
    val thingsToDo: List<ThingToDoWithTimeSpent>
)
