package app.elephantintheroom.somethingtodo.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineThingToDoRepository(
    private val thingToDoDao: ThingToDoDao,
    private val timeSpentDao: TimeSpentDao
) : ThingToDoRepository {
    override fun getAllThingsToDoStream(): Flow<List<ThingToDo>> = thingToDoDao.getAllThingsToDo()

    override fun getActiveThingToDoStream(): Flow<Pair<ThingToDo, TimeSpent>?> =
        thingToDoDao.getActiveThingToDo().map { it.toList().firstOrNull() }

    override fun getActiveThingToDoStream(thingToDo: ThingToDo): Flow<TimeSpent?> =
        thingToDoDao.getActiveThingToDo(thingToDo.id)

    override suspend fun insertThingToDo(thingToDo: ThingToDo) = thingToDoDao.insert(thingToDo)

    override suspend fun insertTimeSpent(timeSpent: TimeSpent) = timeSpentDao.insert(timeSpent)

    override suspend fun updateTimeSpent(timeSpent: TimeSpent) = timeSpentDao.update(timeSpent)
}
