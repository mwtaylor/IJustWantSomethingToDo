package app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elephantintheroom.ijustwantsomethingtodo.core.domain.activethingtodo.OneActiveThingToDoUseCase
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Clock
import java.time.Instant

class WelcomeViewModel(
    private val timeSpentRepository: TimeSpentRepository,
    private val oneActiveThingToDoUseCase: OneActiveThingToDoUseCase,
    private val clock: Clock,
) : ViewModel() {
    val uiState: StateFlow<WelcomeUiState> = emptyList<WelcomeUiState>().asFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        WelcomeUiState
    )

    fun completeThingToDo(thingToDo: ThingToDo) {

    }

    fun startThingToDo(thingToDo: ThingToDo) {
        viewModelScope.launch {
            oneActiveThingToDoUseCase.startThingToDo(thingToDo) { false }
        }
    }

    fun pauseThingToDo(activeThingToDo: ActiveThingToDo) {
        val timeSpent = TimeSpent(
            activeThingToDo.activeTimeSpent.id,
            activeThingToDo.activeTimeSpent.thingToDoId,
            activeThingToDo.activeTimeSpent.started,
            Instant.now(clock),
        )
        viewModelScope.launch {
            timeSpentRepository.recordTimeSpent(timeSpent)
        }
    }
}

data object WelcomeUiState
