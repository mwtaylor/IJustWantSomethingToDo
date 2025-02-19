package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.elephantintheroom.ijustwantsomethingtodo.core.domain.activethingtodo.OneActiveThingToDoUseCase
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoWithTimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui.ThingToDoViewModel
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui.ThingsToDoViewModel
import java.time.Clock

interface ThingToDoApplication {
    val thingToDoRepository: ThingToDoRepository
    val timeSpentRepository: TimeSpentRepository
    val oneActiveThingToDoUseCase: OneActiveThingToDoUseCase
    val clock: Clock
}

object ThingToDoViewModelProvider {
    val THING_TO_DO_KEY = object : CreationExtras.Key<ThingToDoWithTimeSpent> {}

    val factory = viewModelFactory {
        initializer {
            ThingsToDoViewModel(
                thingToDoApplication().thingToDoRepository,
                thingToDoApplication().timeSpentRepository,
                thingToDoApplication().oneActiveThingToDoUseCase,
                thingToDoApplication().clock,
            )
        }
        initializer {
            ThingToDoViewModel(
                this[THING_TO_DO_KEY]!!,
                thingToDoApplication().thingToDoRepository,
                thingToDoApplication().timeSpentRepository,
                thingToDoApplication().oneActiveThingToDoUseCase,
                thingToDoApplication().clock,
            )
        }
    }
}

fun CreationExtras.thingToDoApplication(): ThingToDoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ThingToDoApplication)
