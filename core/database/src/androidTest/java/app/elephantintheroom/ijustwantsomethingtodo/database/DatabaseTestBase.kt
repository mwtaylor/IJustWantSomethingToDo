package app.elephantintheroom.ijustwantsomethingtodo.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.elephantintheroom.ijustwantsomethingtodo.database.dao.ThingToDoDao
import app.elephantintheroom.ijustwantsomethingtodo.database.dao.TimeSpentDao
import org.junit.After
import org.junit.Before

internal abstract class DatabaseTestBase {
    private lateinit var db: AppDatabase
    protected lateinit var thingToDoDao: ThingToDoDao
    protected lateinit var timeSpentDao: TimeSpentDao

    @Before
    fun setup() {
        db = run {
            val context = ApplicationProvider.getApplicationContext<Context>()
            Room.inMemoryDatabaseBuilder(
                context,
                AppDatabase::class.java,
            ).build()
        }

        thingToDoDao = db.thingToDoDao()
        timeSpentDao = db.timeSpentDao()
    }

    @After
    fun teardown() = db.close()
}