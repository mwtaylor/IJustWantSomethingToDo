package app.elephantintheroom.ijustwantsomethingtodo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import app.elephantintheroom.ijustwantsomethingtodo.navigation.WelcomeRoute
import app.elephantintheroom.ijustwantsomethingtodo.ui.AppUiState
import app.elephantintheroom.ijustwantsomethingtodo.ui.welcomeScreen

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
    }
}
