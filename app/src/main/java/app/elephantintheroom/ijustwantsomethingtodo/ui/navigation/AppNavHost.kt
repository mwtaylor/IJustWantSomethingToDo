package app.elephantintheroom.ijustwantsomethingtodo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import app.elephantintheroom.ijustwantsomethingtodo.screens.planning.ui.planningScreen
import app.elephantintheroom.ijustwantsomethingtodo.screens.reports.ui.reportsScreen
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.navigation.WelcomeRoute
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.ui.welcomeScreen
import app.elephantintheroom.ijustwantsomethingtodo.screens.tasks.ui.tasksScreen
import app.elephantintheroom.ijustwantsomethingtodo.ui.AppUiState

@Composable
fun AppNavHost(
    appUiState: AppUiState,
    modifier: Modifier = Modifier,
) {
    val navController = appUiState.navController
    NavHost(
        navController = navController,
        startDestination = WelcomeRoute,
        modifier = modifier,
    ) {
        welcomeScreen()
        tasksScreen()
        planningScreen()
        reportsScreen()
    }
}
