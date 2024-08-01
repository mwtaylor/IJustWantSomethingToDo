package app.elephantintheroom.somethingtodo.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.elephantintheroom.somethingtodo.SomethingToDoApplication
import java.time.Clock

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AppViewModel(
                wantSomethingToDoApplication().container.thingToDoRepository,
                Clock.systemDefaultZone(),
            )
        }
        initializer {
            ThingsToDoViewModel(
                wantSomethingToDoApplication().container.thingToDoRepository,
                Clock.systemDefaultZone(),
            )
        }
        initializer {
            AddThingToDoViewModel(
                wantSomethingToDoApplication().container.thingToDoRepository,
                Clock.systemDefaultZone(),
            )
        }
    }
}

fun CreationExtras.wantSomethingToDoApplication(): SomethingToDoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SomethingToDoApplication)
