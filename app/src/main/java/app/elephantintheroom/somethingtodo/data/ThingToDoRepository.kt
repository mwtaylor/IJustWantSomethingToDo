package app.elephantintheroom.somethingtodo.data

import kotlinx.coroutines.flow.Flow

interface ThingToDoRepository {
    fun getAllThingsToDoStream(): Flow<List<ThingToDo>>

    suspend fun insertThingToDo(thingToDo: ThingToDo)
}
