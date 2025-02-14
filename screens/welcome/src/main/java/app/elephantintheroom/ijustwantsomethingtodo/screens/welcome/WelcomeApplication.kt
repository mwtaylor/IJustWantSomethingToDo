package app.elephantintheroom.ijustwantsomethingtodo.screens.welcome

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.elephantintheroom.ijustwantsomethingtodo.core.domain.activethingtodo.OneActiveThingToDoUseCase
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.ui.WelcomeViewModel
import java.time.Clock

interface WelcomeApplication {
    val timeSpentRepository: TimeSpentRepository
    val oneActiveThingToDoUseCase: OneActiveThingToDoUseCase
    val clock: Clock
}

object WelcomeViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            WelcomeViewModel(
                welcomeApplication().timeSpentRepository,
                welcomeApplication().oneActiveThingToDoUseCase,
                welcomeApplication().clock,
            )
        }
    }
}

fun CreationExtras.welcomeApplication(): WelcomeApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WelcomeApplication)
