package app.elephantintheroom.ijustwantsomethingtodo.data.repository

import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoIncludingActiveTimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import kotlinx.coroutines.flow.Flow

interface ThingToDoRepository {
    fun getAllThingsToDo(): Flow<List<ThingToDo>>

    fun getAllThingsToDoIncludingActiveTimeSpent(): Flow<List<ThingToDoIncludingActiveTimeSpent>>

    fun getAllActiveThingsToDo(): Flow<List<ActiveThingToDo>>

    suspend fun addThingToDo(thingToDo: ThingToDo): ThingToDo
}
