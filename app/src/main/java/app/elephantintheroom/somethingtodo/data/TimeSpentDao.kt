package app.elephantintheroom.somethingtodo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TimeSpentDao {
    @Insert
    suspend fun insert(timeSpent: TimeSpent)

    @Update
    suspend fun update(timeSpent: TimeSpent)
}