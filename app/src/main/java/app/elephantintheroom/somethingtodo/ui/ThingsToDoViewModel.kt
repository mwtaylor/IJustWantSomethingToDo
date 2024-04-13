package app.elephantintheroom.somethingtodo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.ThingToDoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ThingsToDoViewModel(thingToDoRepository: ThingToDoRepository) : ViewModel() {
    val uiState: StateFlow<ThingsToDoUiState> =
        thingToDoRepository.getAllThingsToDoStream().map { ThingsToDoUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ThingsToDoUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ThingsToDoUiState(
    val thingsToDo: List<ThingToDo> = listOf()
)
