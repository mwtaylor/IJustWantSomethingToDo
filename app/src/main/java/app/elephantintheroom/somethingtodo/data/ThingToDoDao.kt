package app.elephantintheroom.somethingtodo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ThingToDoDao {
    @Query("SELECT * from ThingToDo")
    fun getAllThingsToDo(): Flow<List<ThingToDo>>

    @Query(
        "SELECT * from ThingToDo " +
                "JOIN TimeSpent ON ThingToDo.id = TimeSpent.thingToDoId " +
                "WHERE TimeSpent.finished IS NULL " +
                "ORDER BY TimeSpent.started " +
                "LIMIT 1"
    )
    fun getActiveThingToDo(): Flow<Map<ThingToDo, TimeSpent>>

    @Query(
        "SELECT * from TimeSpent " +
                "WHERE finished IS NULL " +
                "AND thingToDoId = :thingToDoId " +
                "ORDER BY started " +
                "LIMIT 1"
    )
    fun getActiveThingToDo(thingToDoId: Int): Flow<TimeSpent?>

    @Insert
    suspend fun insert(thingToDo: ThingToDo)
}
