package app.elephantintheroom.ijustwantsomethingtodo.data.repository

import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent

interface TimeSpentRepository {
    suspend fun addTimeSpent(timeSpent: TimeSpent): TimeSpent
}
