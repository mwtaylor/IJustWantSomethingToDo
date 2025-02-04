package app.elephantintheroom.ijustwantsomethingtodo.screens.reports.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.serialization.Serializable

@Serializable data object ReportsRoute

fun NavController.navigateToReports(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = ReportsRoute) {
        navOptions()
    }
}
