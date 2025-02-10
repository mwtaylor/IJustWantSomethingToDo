package app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.stateIn

class WelcomeViewModel() : ViewModel() {
    val uiState: StateFlow<WelcomeUiState> = emptyList<WelcomeUiState>().asFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        WelcomeUiState
    )
}

data object WelcomeUiState
