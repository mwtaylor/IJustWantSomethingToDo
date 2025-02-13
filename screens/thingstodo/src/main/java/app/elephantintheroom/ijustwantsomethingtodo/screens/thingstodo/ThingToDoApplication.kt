package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.elephantintheroom.ijustwantsomethingtodo.core.domain.activethingtodo.OneActiveThingToDoUseCase
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui.ThingsToDoViewModel

interface ThingToDoApplication {
    val thingToDoRepository: ThingToDoRepository
    val timeSpentRepository: TimeSpentRepository
    val oneActiveThingToDoUseCase: OneActiveThingToDoUseCase
}

object ThingToDoViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            ThingsToDoViewModel(
                thingToDoApplication().thingToDoRepository,
                thingToDoApplication().timeSpentRepository,
                thingToDoApplication().oneActiveThingToDoUseCase,
            )
        }
    }
}

fun CreationExtras.thingToDoApplication(): ThingToDoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ThingToDoApplication)
