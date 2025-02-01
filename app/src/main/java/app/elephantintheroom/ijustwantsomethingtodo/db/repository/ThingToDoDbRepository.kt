package app.elephantintheroom.ijustwantsomethingtodo.db.repository

import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.db.dao.ThingToDoDao
import app.elephantintheroom.ijustwantsomethingtodo.db.entity.ThingToDoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThingToDoDbRepository(
    private val thingToDoDao: ThingToDoDao,
) : ThingToDoRepository {

    override fun getAllThingsToDo(): Flow<List<ThingToDo>> =
        thingToDoDao.getAll().map { allThingsToDo ->
            allThingsToDo.map { it.toModel() }
        }
}

fun ThingToDoEntity.toModel(): ThingToDo {
    return ThingToDo(id, name)
}
