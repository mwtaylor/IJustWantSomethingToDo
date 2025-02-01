package app.elephantintheroom.ijustwantsomethingtodo

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.elephantintheroom.ijustwantsomethingtodo.ui.AppViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AppViewModel(
                somethingToDoApplication().dataContainer.thingToDoRepository,
            )
        }
    }
}

fun CreationExtras.somethingToDoApplication(): SomethingToDoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SomethingToDoApplication)
