package app.elephantintheroom.ijustwantsomethingtodo.app.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AppViewModel(
    private val thingToDoRepository: ThingToDoRepository,
) : ViewModel() {
    val uiState: StateFlow<AppUiState> = thingToDoRepository.getAllActiveThingsToDo().map {
        AppUiState(loading = false, it.firstOrNull())
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        AppUiState(loading = true, null)
    )
}

@Immutable
data class AppUiState(
    val loading: Boolean,
    val activeThingToDo: ActiveThingToDo?,
)
