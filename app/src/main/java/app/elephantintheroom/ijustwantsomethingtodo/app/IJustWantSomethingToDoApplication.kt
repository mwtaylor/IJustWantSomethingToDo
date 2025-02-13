package app.elephantintheroom.ijustwantsomethingtodo.app

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import app.elephantintheroom.ijustwantsomethingtodo.database.AppDatabase
import app.elephantintheroom.ijustwantsomethingtodo.database.repository.ThingToDoDbRepository
import app.elephantintheroom.ijustwantsomethingtodo.database.repository.TimeSpentDbRepository
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ThingToDoApplication
import app.elephantintheroom.ijustwantsomethingtodo.app.ui.AppViewModel
import app.elephantintheroom.ijustwantsomethingtodo.core.domain.activethingtodo.OneActiveThingToDoUseCase
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.WelcomeApplication

class IJustWantSomethingToDoApplication : Application(), WelcomeApplication, ThingToDoApplication {
    override val thingToDoRepository: ThingToDoRepository by lazy {
        ThingToDoDbRepository(
            AppDatabase.getThingToDoDao(this),
        )
    }

    override val timeSpentRepository: TimeSpentRepository by lazy {
        TimeSpentDbRepository(
            AppDatabase.getTimeSpentDao(this)
        )
    }

    override val oneActiveThingToDoUseCase: OneActiveThingToDoUseCase by lazy {
        OneActiveThingToDoUseCase(
            thingToDoRepository,
            timeSpentRepository,
            AppDatabase.getTransactionProvider(this),
        )
    }
}

object AppViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            AppViewModel(
                application().thingToDoRepository,
            )
        }
    }
}

fun CreationExtras.application(): IJustWantSomethingToDoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as IJustWantSomethingToDoApplication)
