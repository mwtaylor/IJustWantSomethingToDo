package app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoWithActiveTimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant

class WelcomeViewModel(
    private val timeSpentRepository: TimeSpentRepository,
) : ViewModel() {
    val uiState: StateFlow<WelcomeUiState> = emptyList<WelcomeUiState>().asFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        WelcomeUiState
    )

    fun completeThingToDo(thingToDo: ThingToDo) {

    }

    fun startThingToDo(thingToDo: ThingToDo) {
        val timeSpent = TimeSpent(null, thingToDo.id!!, Instant.now(), null)
        viewModelScope.launch {
            timeSpentRepository.addTimeSpent(timeSpent)
        }
    }

    fun pauseThingToDo(thingToDoWithActiveTimeSpent: ThingToDoWithActiveTimeSpent) {
        val timeSpent = TimeSpent(
            thingToDoWithActiveTimeSpent.activeTimeSpent.id,
            thingToDoWithActiveTimeSpent.activeTimeSpent.thingToDoId,
            thingToDoWithActiveTimeSpent.activeTimeSpent.started,
            Instant.now(),
        )
        viewModelScope.launch {
            timeSpentRepository.addTimeSpent(timeSpent)
        }
    }
}

data object WelcomeUiState
