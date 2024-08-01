package app.elephantintheroom.somethingtodo.ui

import androidx.lifecycle.ViewModel
import app.elephantintheroom.somethingtodo.data.ThingToDoRepository
import java.time.Clock

class AddThingToDoViewModel(private val thingToDoRepository: ThingToDoRepository, private val clock: Clock)
    : BaseViewModel(thingToDoRepository, clock), ViewModelCanAddANewThingToDo by ViewModelCanAddANewThingToDoQ(thingToDoRepository, clock) {

}