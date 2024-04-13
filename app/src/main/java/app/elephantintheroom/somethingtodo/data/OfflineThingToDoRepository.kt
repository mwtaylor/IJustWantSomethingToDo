package app.elephantintheroom.somethingtodo.data

import kotlinx.coroutines.flow.Flow

class OfflineThingToDoRepository(private val thingToDoDao: ThingToDoDao) : ThingToDoRepository {
    override fun getAllThingsToDoStream(): Flow<List<ThingToDo>> = thingToDoDao.getAllThingsToDo()

    override suspend fun insertThingToDo(thingToDo: ThingToDo) = thingToDoDao.insert(thingToDo)
}
