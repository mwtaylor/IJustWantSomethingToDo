package app.elephantintheroom.ijustwantsomethingtodo.db

import android.content.Context
import app.elephantintheroom.ijustwantsomethingtodo.data.AppDataContainer
import app.elephantintheroom.ijustwantsomethingtodo.db.repository.ThingToDoDbRepository
import app.elephantintheroom.ijustwantsomethingtodo.data.repository.ThingToDoRepository

class AppDataDbContainer(private val context: Context) : AppDataContainer {
    override val thingToDoRepository: ThingToDoRepository by lazy {
        ThingToDoDbRepository(
            AppDatabase.getDatabase(context).thingToDoDao(),
        )
    }
}
