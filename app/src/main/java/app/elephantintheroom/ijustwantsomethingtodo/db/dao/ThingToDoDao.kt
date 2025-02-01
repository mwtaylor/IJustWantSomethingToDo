package app.elephantintheroom.ijustwantsomethingtodo.db.dao

import androidx.room.Dao
import androidx.room.Query
import app.elephantintheroom.ijustwantsomethingtodo.db.entity.ThingToDoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ThingToDoDao {
    @Query("SELECT * FROM things_to_do")
    fun getAll(): Flow<List<ThingToDoEntity>>
}
