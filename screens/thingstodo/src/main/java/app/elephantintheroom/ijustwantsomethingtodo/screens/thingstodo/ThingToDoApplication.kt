package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui.ThingsToDoViewModel

interface ThingToDoApplication {
    val thingToDoRepository: ThingToDoRepository
}

object ThingToDoViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            ThingsToDoViewModel(
                thingToDoApplication().thingToDoRepository
            )
        }
    }
}

fun CreationExtras.thingToDoApplication(): ThingToDoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ThingToDoApplication)
