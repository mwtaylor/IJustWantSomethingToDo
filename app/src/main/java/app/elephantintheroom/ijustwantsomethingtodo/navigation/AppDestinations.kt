package app.elephantintheroom.ijustwantsomethingtodo.navigation

import androidx.annotation.StringRes
import androidx.navigation.NavController
import app.elephantintheroom.ijustwantsomethingtodo.R
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.navigation.WelcomeRoute
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.navigation.navigateToWelcome
import app.elephantintheroom.ijustwantsomethingtodo.screens.tasks.navigation.TasksRoute
import app.elephantintheroom.ijustwantsomethingtodo.screens.tasks.navigation.navigateToTasks
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
        route = TasksRoute::class,
        onClick = { it.navigateToTasks() },
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
