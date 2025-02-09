package app.elephantintheroom.ijustwantsomethingtodo.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.TimeSpentEntity

@Dao
interface TimeSpentDao {
    @Query("""
        SELECT * 
        FROM time_spent 
        WHERE time_spent_thing_to_do_id = :thingToDoId AND time_spent_ended IS NULL 
        LIMIT 1
    """)
    suspend fun getActiveForThingToDo(thingToDoId: Long): TimeSpentEntity?

    @Query("SELECT * FROM time_spent WHERE time_spent_id = :id LIMIT 1")
    suspend fun get(id: Long): TimeSpentEntity

    @Upsert
    suspend fun record(timeSpentEntity: TimeSpentEntity): Long
}