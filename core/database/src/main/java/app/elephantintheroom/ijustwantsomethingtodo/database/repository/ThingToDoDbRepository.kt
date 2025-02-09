package app.elephantintheroom.ijustwantsomethingtodo.database.repository

import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoIncludingActiveTimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.database.dao.ThingToDoDao
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoIncludingActiveTimeSpentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThingToDoDbRepository(
    private val thingToDoDao: ThingToDoDao,
) : ThingToDoRepository {

    override fun getAllThingsToDo(): Flow<List<ThingToDo>> =
        thingToDoDao.getAll().map { allThingToDoEntities ->
            allThingToDoEntities.map { it.toModel() }
        }

    override fun getAllThingsToDoIncludingActiveTimeSpent(): Flow<List<ThingToDoIncludingActiveTimeSpent>> =
        thingToDoDao.getAllIncludingActiveTimeSpent().map { allThingToDoIncludingActiveTimeSpentEntities ->
            allThingToDoIncludingActiveTimeSpentEntities.map { it.toModel() }
        }

    override suspend fun addThingToDo(thingToDo: ThingToDo): ThingToDo {
        val id = thingToDoDao.insert(ThingToDoEntity(null, thingToDo.name))
        return thingToDoDao.get(id).toModel()
    }
}

fun ThingToDoEntity.toModel(): ThingToDo {
    return ThingToDo(id, name)
}

fun ThingToDoIncludingActiveTimeSpentEntity.toModel(): ThingToDoIncludingActiveTimeSpent {
    return ThingToDoIncludingActiveTimeSpent(
        thingToDoEntity.toModel(),
        activeTimeSpentEntity?.toModel(),
    )
}
