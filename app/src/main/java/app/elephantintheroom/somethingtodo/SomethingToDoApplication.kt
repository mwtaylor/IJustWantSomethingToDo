package app.elephantintheroom.somethingtodo

import android.app.Application
import app.elephantintheroom.somethingtodo.data.AppContainer
import app.elephantintheroom.somethingtodo.data.AppDataContainer

class SomethingToDoApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
