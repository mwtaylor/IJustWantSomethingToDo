package app.elephantintheroom.somethingtodo.ui

import androidx.lifecycle.viewModelScope
import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.ThingToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Clock

interface ViewModelCanAddANewThingToDo {
    fun addThingToDo(thingToDo: ThingToDo): Unit
}

class ViewModelCanAddANewThingToDoQ(private val thingToDoRepository: ThingToDoRepository, private val clock: Clock)
    : app.elephantintheroom.somethingtodo.ui.ViewModelCanAddANewThingToDo, BaseViewModel(thingToDoRepository, clock) {
    override fun addThingToDo(thingToDo: ThingToDo) {
        viewModelScope.launch(Dispatchers.IO) {
            thingToDoRepository.insertThingToDo(thingToDo)
        }
    }
}
