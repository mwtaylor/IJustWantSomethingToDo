package app.elephantintheroom.ijustwantsomethingtodo.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoIncludingActiveTimeSpentEntity
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoWithActiveTimeSpentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ThingToDoDao {
    @Query("SELECT * FROM things_to_do")
    fun getAll(): Flow<List<ThingToDoEntity>>

    @Query("""
        SELECT *
        FROM things_to_do
        LEFT JOIN time_spent 
        ON time_spent_id = (
            SELECT time_spent_id
            FROM time_spent 
            WHERE time_spent_thing_to_do_id = thing_to_do_id AND time_spent_ended IS NULL 
            LIMIT 1
        )
    """)
    fun getAllIncludingActiveTimeSpent(): Flow<List<ThingToDoIncludingActiveTimeSpentEntity>>

    @Query("""
        SELECT *
        FROM things_to_do
        JOIN time_spent
        ON thing_to_do_id = time_spent_thing_to_do_id
        WHERE time_spent_started IS NOT NULL AND time_spent_ended IS NULL
    """)
    fun getAllWithActiveTimeSpent(): Flow<List<ThingToDoWithActiveTimeSpentEntity>>

    @Query("SELECT * FROM things_to_do WHERE thing_to_do_id = :id LIMIT 1")
    suspend fun get(id: Long): ThingToDoEntity

    @Insert
    suspend fun insert(thingToDoEntity: ThingToDoEntity): Long

    @Insert
    suspend fun insert(thingToDoEntities: List<ThingToDoEntity>): List<Long>
}
