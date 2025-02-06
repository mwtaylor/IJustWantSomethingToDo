package app.elephantintheroom.ijustwantsomethingtodo.database.repository

import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.database.dao.ThingToDoDao
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThingToDoDbRepository(
    private val thingToDoDao: ThingToDoDao,
) : ThingToDoRepository {

    override fun getAllThingsToDo(): Flow<List<ThingToDo>> =
        thingToDoDao.getAll().map { allThingsToDo ->
            allThingsToDo.map { it.toModel() }
        }

    override suspend fun addThingToDo(thingToDo: ThingToDo): ThingToDo {
        val id = thingToDoDao.insert(ThingToDoEntity(null, thingToDo.name))
        return thingToDoDao.get(id).toModel()
    }
}

fun ThingToDoEntity.toModel(): ThingToDo {
    return ThingToDo(id, name)
}
