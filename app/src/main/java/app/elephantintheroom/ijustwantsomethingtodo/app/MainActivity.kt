package app.elephantintheroom.ijustwantsomethingtodo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.elephantintheroom.ijustwantsomethingtodo.app.ui.App
import app.elephantintheroom.ijustwantsomethingtodo.app.ui.AppUiState
import app.elephantintheroom.ijustwantsomethingtodo.app.ui.AppViewModel
import app.elephantintheroom.ijustwantsomethingtodo.app.ui.theme.IJustWantSomethingToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController: NavHostController = rememberNavController()
            val viewModel: AppViewModel = viewModel(factory = AppViewModelProvider.factory)
            val uiState: AppUiState by viewModel.uiState.collectAsStateWithLifecycle()

            ThemedScreen(
                uiState,
                navController = navController
            )
        }
    }

    @Composable
    fun ThemedScreen(
        appUiState: AppUiState,
        navController: NavHostController,
        modifier: Modifier = Modifier,
    ) {
        IJustWantSomethingToDoTheme {
            App(
                appUiState,
                navController,
                modifier = modifier
            )
        }
    }
}
