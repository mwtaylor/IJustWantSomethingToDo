package app.elephantintheroom.somethingtodo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ThingToDoDao {
    @Query("SELECT * from thing_to_do")
    fun getAllThingsToDo(): Flow<List<ThingToDo>>

    @Insert
    suspend fun insert(thingToDo: ThingToDo)
}
