package app.elephantintheroom.ijustwantsomethingtodo.data.repository

import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoIncludingActiveTimeSpent
import kotlinx.coroutines.flow.Flow

interface ThingToDoRepository {
    fun getAllThingsToDo(): Flow<List<ThingToDo>>

    fun getAllThingsToDoIncludingActiveTimeSpent(): Flow<List<ThingToDoIncludingActiveTimeSpent>>

    suspend fun addThingToDo(thingToDo: ThingToDo): ThingToDo
}
