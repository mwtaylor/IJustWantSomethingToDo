package app.elephantintheroom.somethingtodo.ui

import androidx.lifecycle.ViewModel
import app.elephantintheroom.somethingtodo.data.ThingToDoRepository
import java.time.Clock

open class BaseViewModel(private val thingToDoRepository: ThingToDoRepository, private val clock: Clock) : ViewModel() {
    companion object {
        internal const val TIMEOUT_MILLIS = 5_000L
    }
}