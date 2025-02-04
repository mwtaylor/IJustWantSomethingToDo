package app.elephantintheroom.ijustwantsomethingtodo.screens.tasks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.serialization.Serializable

@Serializable data object TasksRoute

fun NavController.navigateToTasks(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = TasksRoute) {
        navOptions()
    }
}
