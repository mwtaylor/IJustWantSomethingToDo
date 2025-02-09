package app.elephantintheroom.ijustwantsomethingtodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.elephantintheroom.ijustwantsomethingtodo.database.converter.TimeConverters
import app.elephantintheroom.ijustwantsomethingtodo.database.dao.ThingToDoDao
import app.elephantintheroom.ijustwantsomethingtodo.database.dao.TimeSpentDao
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.ThingToDoEntity
import app.elephantintheroom.ijustwantsomethingtodo.database.entity.TimeSpentEntity

@Database(
    version = 4,
    entities = [
        ThingToDoEntity::class,
        TimeSpentEntity::class,
    ],
    exportSchema = true,
)
@TypeConverters(TimeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun thingToDoDao(): ThingToDoDao
    abstract fun timeSpentDao(): TimeSpentDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        private fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }

        fun getThingToDoDao(context: Context): ThingToDoDao {
            return getDatabase(context).thingToDoDao()
        }

        fun getTimeSpentDao(context: Context): TimeSpentDao {
            return getDatabase(context).timeSpentDao()
        }
    }
}
