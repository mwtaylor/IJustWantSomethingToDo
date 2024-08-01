package app.elephantintheroom.somethingtodo.ui

import app.elephantintheroom.somethingtodo.data.ThingToDoRepository
import java.time.Clock

class AppViewModel(private val thingToDoRepository: ThingToDoRepository, private val clock: Clock)
    : BaseViewModel(thingToDoRepository, clock)