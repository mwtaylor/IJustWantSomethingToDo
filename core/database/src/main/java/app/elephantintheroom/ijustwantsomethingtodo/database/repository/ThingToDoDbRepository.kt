package app.elephantintheroom.ijustwantsomethingtodo.database.repository

import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoWithTimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.InactiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.database.dao.ThingToDoDao
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoWithTimeSpentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThingToDoDbRepository(
    private val thingToDoDao: ThingToDoDao,
) : ThingToDoRepository {

    override fun getAllThingsToDo(): Flow<List<ThingToDo>> =
        thingToDoDao.getAll().map { allThingToDoEntities ->
            allThingToDoEntities.map { it.toModel() }
        }

    override fun getAllThingsToDoWithTimeSpent(): Flow<List<ThingToDoWithTimeSpent>> =
        thingToDoDao.getAllWithTimeSpent().map { thingToDo ->
            thingToDo.map { it.toModel() }
        }

    override fun getAllActiveThingsToDo(): Flow<List<ActiveThingToDo>> =
        thingToDoDao.getAllWithTimeSpent().map { thingsToDo ->
            thingsToDo.mapNotNull {
                val model = it.toModel()
                if (model is ActiveThingToDo) {
                    model
                } else {
                    null
                }
            }
        }

    override suspend fun addThingToDo(thingToDo: ThingToDo): ThingToDoWithTimeSpent {
        val id = thingToDoDao.insert(ThingToDoEntity(null, thingToDo.name))
        return thingToDoDao.get(id).toModel()
    }
}

fun ThingToDoEntity.toModel(): ThingToDo {
    return ThingToDo(id, name)
}

fun ThingToDoWithTimeSpentEntity.toModel(): ThingToDoWithTimeSpent {
    val timeSpentModels = timeSpent.map { it.toModel() }
    val (activeTimeSpent, concludedTimeSpent) = timeSpentModels.partition { it.ended == null }
    return if (activeTimeSpent.isEmpty()) {
        InactiveThingToDo(
            thingToDoEntity.toModel(),
            concludedTimeSpent,
        )
    } else {
        ActiveThingToDo(
            thingToDoEntity.toModel(),
            activeTimeSpent.single(),
            concludedTimeSpent,
        )
    }
}
