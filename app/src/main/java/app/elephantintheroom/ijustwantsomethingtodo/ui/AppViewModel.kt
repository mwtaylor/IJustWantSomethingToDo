package app.elephantintheroom.ijustwantsomethingtodo.ui

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        AppUiState(
            "Android",
            listOf("Work", "Play"),
        )
    )
    val uiState: StateFlow<AppUiState> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        _uiState.value
    )

    fun startTask(name: String) {

    }
}

@Immutable
data class AppUiState(
    val name: String,
    val tasks: List<String>,
)
