package app.elephantintheroom.ijustwantsomethingtodo.app.navigation

import androidx.annotation.StringRes
import androidx.navigation.NavController
import app.elephantintheroom.ijustwantsomethingtodo.app.R
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.navigation.WelcomeRoute
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.navigation.navigateToWelcome
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.navigation.ThingsToDoRoute
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.navigation.navigateToThingsToDo
import app.elephantintheroom.ijustwantsomethingtodo.screens.planning.navigation.PlanningRoute
import app.elephantintheroom.ijustwantsomethingtodo.screens.planning.navigation.navigateToPlanning
import app.elephantintheroom.ijustwantsomethingtodo.screens.reports.navigation.ReportsRoute
import app.elephantintheroom.ijustwantsomethingtodo.screens.reports.navigation.navigateToReports
import kotlin.reflect.KClass

enum class AppDestinations(
    @StringRes val label: Int,
    val route: KClass<*>,
    val onClick: (NavController) -> Unit,
) {
    WELCOME(
        label = R.string.welcome,
        route = WelcomeRoute::class,
        onClick = { it.navigateToWelcome() },
    ),
    TASKS(
        label = R.string.tasks,
        route = ThingsToDoRoute::class,
        onClick = { it.navigateToThingsToDo() },
    ),
    PLANNING(
        label = R.string.planning,
        route = PlanningRoute::class,
        onClick = { it.navigateToPlanning() },
    ),
    REPORTS(
        label = R.string.reports,
        route = ReportsRoute::class,
        onClick = { it.navigateToReports() },
    )
}
