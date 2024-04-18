package app.elephantintheroom.somethingtodo.data

import kotlinx.coroutines.flow.Flow

interface ThingToDoRepository {
    fun getAllThingsToDoStream(): Flow<List<ThingToDo>>

    fun getActiveThingToDoStream(): Flow<Pair<ThingToDo, TimeSpent>?>

    fun getActiveThingToDoStream(thingToDo: ThingToDo): Flow<TimeSpent?>

    suspend fun insertThingToDo(thingToDo: ThingToDo)

    suspend fun insertTimeSpent(timeSpent: TimeSpent)

    suspend fun updateTimeSpent(timeSpent: TimeSpent)
}
