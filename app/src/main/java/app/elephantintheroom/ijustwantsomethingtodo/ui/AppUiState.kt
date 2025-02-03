package app.elephantintheroom.ijustwantsomethingtodo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppUiState(
    navController: NavHostController = rememberNavController(),
): AppUiState {
    return remember(navController) {
        AppUiState(navController)
    }
}

@Immutable
data class AppUiState(
    val navController: NavHostController,
)
