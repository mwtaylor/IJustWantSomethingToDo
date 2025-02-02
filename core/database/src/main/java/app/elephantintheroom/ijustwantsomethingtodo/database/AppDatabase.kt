package app.elephantintheroom.ijustwantsomethingtodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.elephantintheroom.ijustwantsomethingtodo.database.dao.ThingToDoDao
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity

@Database(
    version = 1,
    entities = [ThingToDoEntity::class],
    exportSchema = true,
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun thingToDoDao(): ThingToDoDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
