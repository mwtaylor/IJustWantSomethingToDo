package app.elephantintheroom.somethingtodo.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import app.elephantintheroom.somethingtodo.SomethingToDoApplication

object ThingsToDoViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ThingsToDoViewModel(wantSomethingToDoApplication().container.thingToDoRepository)
        }
    }
}

fun CreationExtras.wantSomethingToDoApplication(): SomethingToDoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as SomethingToDoApplication)
