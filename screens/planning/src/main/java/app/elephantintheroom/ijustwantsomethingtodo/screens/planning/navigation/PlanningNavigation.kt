package app.elephantintheroom.ijustwantsomethingtodo.screens.planning.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.serialization.Serializable

@Serializable data object PlanningRoute

fun NavController.navigateToPlanning(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = PlanningRoute) {
        navOptions()
    }
}
