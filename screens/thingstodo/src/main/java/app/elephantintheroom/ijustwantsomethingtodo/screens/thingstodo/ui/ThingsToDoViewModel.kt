package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant

class ThingsToDoViewModel(
    private val thingToDoRepository: ThingToDoRepository,
    private val timeSpentRepository: TimeSpentRepository,
) : ViewModel() {
    val uiState: StateFlow<ThingsToDoUiState> = thingToDoRepository.getAllThingsToDo().map {
        ThingsToDoUiState(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ThingsToDoUiState(emptyList())
    )

    fun addThingToDo(thingToDo: ThingToDo, onComplete: (ThingToDo) -> Unit = {}) {
        viewModelScope.launch {
            val newThingToDo = thingToDoRepository.addThingToDo(thingToDo)
            onComplete(newThingToDo)
        }
    }

    fun startSpendingTime(thingToDoId: Long) {
        val timeSpent = TimeSpent(null, thingToDoId, Instant.now(), null)
        viewModelScope.launch {
            timeSpentRepository.addTimeSpent(timeSpent)
        }
    }
}

data class ThingsToDoUiState(
    val thingsToDo: List<ThingToDo>
)
