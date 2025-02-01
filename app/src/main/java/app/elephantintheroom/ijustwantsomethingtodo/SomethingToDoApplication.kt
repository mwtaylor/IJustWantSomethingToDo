package app.elephantintheroom.ijustwantsomethingtodo

import android.app.Application
import app.elephantintheroom.ijustwantsomethingtodo.data.AppDataContainer
import app.elephantintheroom.ijustwantsomethingtodo.db.AppDataDbContainer

class SomethingToDoApplication : Application() {
    lateinit var dataContainer: AppDataContainer

    override fun onCreate() {
        super.onCreate()
        dataContainer = AppDataDbContainer(this)
    }
}
