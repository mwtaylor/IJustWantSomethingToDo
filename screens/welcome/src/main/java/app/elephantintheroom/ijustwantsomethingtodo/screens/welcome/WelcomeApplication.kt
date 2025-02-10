package app.elephantintheroom.ijustwantsomethingtodo.screens.welcome

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.ui.WelcomeViewModel

interface WelcomeApplication {
}

object WelcomeViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            WelcomeViewModel()
        }
    }
}

fun CreationExtras.welcomeApplication(): WelcomeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WelcomeApplication)
