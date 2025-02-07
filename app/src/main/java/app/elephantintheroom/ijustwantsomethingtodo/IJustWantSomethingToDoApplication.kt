package app.elephantintheroom.ijustwantsomethingtodo

import android.app.Application
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.TimeSpentRepository
import app.elephantintheroom.ijustwantsomethingtodo.database.AppDatabase
import app.elephantintheroom.ijustwantsomethingtodo.database.repository.ThingToDoDbRepository
import app.elephantintheroom.ijustwantsomethingtodo.database.repository.TimeSpentDbRepository
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ThingToDoApplication

class IJustWantSomethingToDoApplication : Application(), ThingToDoApplication {
    override val thingToDoRepository: ThingToDoRepository by lazy {
        ThingToDoDbRepository(
            AppDatabase.getThingToDoDao(this),
        )
    }

    override val timeSpentRepository: TimeSpentRepository by lazy {
        TimeSpentDbRepository(
            AppDatabase.getTimeSpentDao(this)
        )
    }
}
