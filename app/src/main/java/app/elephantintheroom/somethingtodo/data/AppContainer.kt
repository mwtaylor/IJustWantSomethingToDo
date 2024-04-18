package app.elephantintheroom.somethingtodo.data

import android.content.Context

interface AppContainer {
    val thingToDoRepository: ThingToDoRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val thingToDoRepository: ThingToDoRepository by lazy {
        OfflineThingToDoRepository(
            AppDatabase.getDatabase(context).thingToDoDao(),
            AppDatabase.getDatabase(context).timeSpentDao(),
        )
    }
}
