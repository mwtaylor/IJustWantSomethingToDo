package app.elephantintheroom.ijustwantsomethingtodo.database.repository

import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import app.elephantintheroom.ijustwantsomethingtodo.database.dao.TimeSpentDao
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.TimeSpentEntity

class TimeSpentDbRepository(
    private val timeSpentDao: TimeSpentDao,
) : TimeSpentRepository {
    override suspend fun addTimeSpent(timeSpent: TimeSpent): TimeSpent {
        val result = timeSpentDao.record(
            TimeSpentEntity(
                timeSpent.id,
                timeSpent.thingToDoId,
                timeSpent.started,
                timeSpent.ended,
            )
        )
        val id = timeSpent.id ?: result
        val newTimeSpentEntity = timeSpentDao.get(id)
        return newTimeSpentEntity.toModel()
    }
}

fun TimeSpentEntity.toModel(): TimeSpent {
    return TimeSpent(
        id,
        thingToDoId,
        started,
        ended,
    )
}
