package app.elephantintheroom.ijustwantsomethingtodo.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AppViewModel(
    thingToDoRepository: ThingToDoRepository,
) : ViewModel() {
    private val _uiState = thingToDoRepository.getAllThingsToDo().map {
        allThingsToDo ->
            AppUiState(
                "Android",
                allThingsToDo.map { it.name },
            )
    }
    val uiState: StateFlow<AppUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        AppUiState(
            "Loading",
            emptyList(),
        )
    )

    fun startTask(name: String) {

    }
}

@Immutable
data class AppUiState(
    val name: String,
    val tasks: List<String>,
)
