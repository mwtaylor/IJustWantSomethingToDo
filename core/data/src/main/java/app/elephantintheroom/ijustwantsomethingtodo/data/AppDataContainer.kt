package app.elephantintheroom.ijustwantsomethingtodo.data

import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository

interface AppDataContainer {
    val thingToDoRepository: ThingToDoRepository
}
