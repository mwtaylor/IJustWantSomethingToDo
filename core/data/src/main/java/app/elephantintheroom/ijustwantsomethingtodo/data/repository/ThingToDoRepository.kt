package app.elephantintheroom.ijustwantsomethingtodo.data.repository

import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import kotlinx.coroutines.flow.Flow

interface ThingToDoRepository {
    fun getAllThingsToDo(): Flow<List<ThingToDo>>

    suspend fun addThingToDo(thingToDo: ThingToDo): ThingToDo
}
