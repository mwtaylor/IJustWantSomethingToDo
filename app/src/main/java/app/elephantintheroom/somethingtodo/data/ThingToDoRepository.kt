package app.elephantintheroom.somethingtodo.data

import kotlinx.coroutines.flow.Flow
import java.time.Instant

interface ThingToDoRepository {
    fun getAllThingsToDoStream(): Flow<List<ThingToDo>>

    fun getActiveThingToDoStream(): Flow<Pair<ThingToDo, TimeSpent>?>

    fun getActiveThingToDoStream(thingToDo: ThingToDo): Flow<TimeSpent?>

    fun getRecentTimeSpentOnThingToDo(thingToDo: ThingToDo, timeSpentSince: Instant): Flow<List<TimeSpent>>

    suspend fun insertThingToDo(thingToDo: ThingToDo)

    suspend fun insertTimeSpent(timeSpent: TimeSpent)

    suspend fun updateTimeSpent(timeSpent: TimeSpent)
}
