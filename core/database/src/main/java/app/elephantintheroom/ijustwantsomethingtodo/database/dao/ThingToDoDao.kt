package app.elephantintheroom.ijustwantsomethingtodo.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoWithTimeSpentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ThingToDoDao {
    @Query("SELECT * FROM things_to_do")
    fun getAll(): Flow<List<ThingToDoEntity>>

    @Transaction
    @Query("""
        SELECT *
        FROM things_to_do
    """)
    fun getAllWithTimeSpent(): Flow<List<ThingToDoWithTimeSpentEntity>>

    @Transaction
    @Query("""
        SELECT *
        FROM things_to_do
        WHERE thing_to_do_id = :id
    """)
    fun getWithTimeSpent(id: Long): Flow<ThingToDoWithTimeSpentEntity>

    @Insert
    suspend fun insert(thingToDoEntity: ThingToDoEntity): Long

    @Insert
    suspend fun insert(thingToDoEntities: List<ThingToDoEntity>): List<Long>
}
