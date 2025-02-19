package app.elephantintheroom.ijustwantsomethingtodo.database.repository

import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoWithTimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ExistingThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.InactiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.NewThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.database.dao.ThingToDoDao
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoWithTimeSpentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ThingToDoDbRepository(
    private val thingToDoDao: ThingToDoDao,
) : ThingToDoRepository {
    override fun getWithTimeSpent(id: Long): Flow<ThingToDoWithTimeSpent> =
        thingToDoDao.getWithTimeSpent(id).map { it.toModel() }

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
        return thingToDoDao.getWithTimeSpent(id).first().toModel()
    }
}

fun ThingToDoEntity.toModel(): ThingToDo {
    return if (id == null) {
        NewThingToDo(name = name)
    } else {
        ExistingThingToDo(id, name)
    }
}

fun ThingToDoWithTimeSpentEntity.toModel(): ThingToDoWithTimeSpent {
    val thingToDoModel = thingToDoEntity.toModel() as ExistingThingToDo
    val timeSpentModels = timeSpent.map { it.toModel() }
    val (activeTimeSpent, concludedTimeSpent) = timeSpentModels.partition { it.ended == null }
    return if (activeTimeSpent.isEmpty()) {
        InactiveThingToDo(
            thingToDoModel,
            concludedTimeSpent,
        )
    } else {
        ActiveThingToDo(
            thingToDoModel,
            activeTimeSpent.single(),
            concludedTimeSpent,
        )
    }
}
