package app.elephantintheroom.ijustwantsomethingtodo.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.TimeSpentEntity

@Dao
interface TimeSpentDao {
    @Query("SELECT * FROM time_spent WHERE thing_to_do_id = :thingToDoId AND ended IS NULL LIMIT 1")
    suspend fun getActiveForThingToDo(thingToDoId: Long): TimeSpentEntity

    @Query("SELECT * FROM time_spent WHERE id = :id LIMIT 1")
    suspend fun get(id: Long): TimeSpentEntity

    @Insert
    suspend fun insert(timeSpentEntity: TimeSpentEntity): Long
}