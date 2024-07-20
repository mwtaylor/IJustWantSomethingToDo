package app.elephantintheroom.somethingtodo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeSpentDao {
    @Query(
        "SELECT * FROM TimeSpent " +
                "WHERE thingToDoId = :thingToDoId " +
                "AND finished > :timeSpentSince"
    )
    fun getRecentTimeSpentOnThingToDo(thingToDoId: Int, timeSpentSince: Long): Flow<List<TimeSpent>>

    @Insert
    suspend fun insert(timeSpent: TimeSpent)

    @Update
    suspend fun update(timeSpent: TimeSpent)
}