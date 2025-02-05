package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ThingsToDoViewModel(
    thingToDoRepository: ThingToDoRepository
) : ViewModel() {
    val uiState: StateFlow<ThingsToDoUiState> = thingToDoRepository.getAllThingsToDo().map {
        ThingsToDoUiState(it)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ThingsToDoUiState(emptyList())
    )
}

data class ThingsToDoUiState(
    val thingsToDo: List<ThingToDo>
)
