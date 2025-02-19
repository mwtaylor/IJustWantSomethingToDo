package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elephantintheroom.ijustwantsomethingtodo.core.domain.activethingtodo.OneActiveThingToDoUseCase
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

class ThingToDoViewModel(
    private val initialThingToDoWithTimeSpent: ThingToDoWithTimeSpent,
    private val thingToDoRepository: ThingToDoRepository,
    private val timeSpentRepository: TimeSpentRepository,
    private val oneActiveThingToDoUseCase: OneActiveThingToDoUseCase,
    private val clock: Clock,
) : ViewModel(), ThingToDoViewModelBase {
    val uiState: StateFlow<ThingToDoUiState> = thingToDoRepository.getWithTimeSpent(
        initialThingToDoWithTimeSpent.thingToDo.id,
    ).map {
        ThingToDoUiState(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ThingToDoUiState(initialThingToDoWithTimeSpent)
    )

    fun startSpendingTime() {
        viewModelScope.launch {
            _startSpendingTime(uiState.value.thingToDo.thingToDo, oneActiveThingToDoUseCase)
        }
    }

    fun stopSpendingTime(timeSpent: TimeSpent) {
        viewModelScope.launch {
            _stopSpendingTime(timeSpent, timeSpentRepository, clock)
        }
    }
}

data class ThingToDoUiState(
    val thingToDo: ThingToDoWithTimeSpent,
)
