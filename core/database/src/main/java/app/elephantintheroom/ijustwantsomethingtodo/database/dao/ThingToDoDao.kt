package app.elephantintheroom.ijustwantsomethingtodo.database.dao

import androidx.room.Dao
import androidx.room.Query
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ThingToDoDao {
    @Query("SELECT * FROM things_to_do")
    fun getAll(): Flow<List<ThingToDoEntity>>
}
