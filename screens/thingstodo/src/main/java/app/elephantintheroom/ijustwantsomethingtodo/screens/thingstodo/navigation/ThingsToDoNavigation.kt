package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.serialization.Serializable

@Serializable data object ThingsToDoRoute

fun NavController.navigateToThingsToDo(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = ThingsToDoRoute) {
        navOptions()
    }
}
