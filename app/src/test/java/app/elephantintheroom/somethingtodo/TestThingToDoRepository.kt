package app.elephantintheroom.somethingtodo

import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.ThingToDoRepository
import app.elephantintheroom.somethingtodo.data.TimeSpent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import java.time.Instant

class TestThingToDoRepository(val initialThingsToDo: List<ThingToDo>, val recentTimeSpent: List<TimeSpent> = listOf()) : ThingToDoRepository {
    override fun getAllThingsToDoStream(): Flow<List<ThingToDo>> {
        return listOf(initialThingsToDo).asFlow()
    }

    override fun getActiveThingToDoStream(): Flow<Pair<ThingToDo, TimeSpent>?> {
        return listOf(null).asFlow()
    }

    override fun getActiveThingToDoStream(thingToDo: ThingToDo): Flow<TimeSpent?> {
        return listOf(null).asFlow()
    }

    override fun getRecentTimeSpentOnThingToDo(
        thingToDo: ThingToDo,
        timeSpentSince: Instant,
    ): Flow<List<TimeSpent>> {
        return listOf(recentTimeSpent).asFlow()
    }

    override suspend fun insertThingToDo(thingToDo: ThingToDo) {
        TODO("Not yet implemented")
    }

    override suspend fun insertTimeSpent(timeSpent: TimeSpent) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTimeSpent(timeSpent: TimeSpent) {
        TODO("Not yet implemented")
    }

}