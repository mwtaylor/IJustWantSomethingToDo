package app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.ui

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.WelcomeViewModelProvider
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.navigation.WelcomeRoute
import app.elephantintheroom.ijustwantsomethingtodo.ui.common.LocalClock

fun NavGraphBuilder.welcomeScreen(
    navController: NavController,
    activeThingToDo: ActiveThingToDo?,
) {
    composable<WelcomeRoute> { backStackEntry ->
        val baseEntry = remember(backStackEntry) {
            navController.getBackStackEntry(WelcomeRoute)
        }

        val viewModel: WelcomeViewModel = viewModel(
            baseEntry,
            factory = WelcomeViewModelProvider.factory,
        )
        val uiState: WelcomeUiState by viewModel.uiState.collectAsStateWithLifecycle()

        CompositionLocalProvider(LocalClock provides viewModel.clock) {
            WelcomeScreen(
                uiState,
                activeThingToDo,
                onThingToDoComplete = viewModel::completeThingToDo,
                onThingToDoStart = viewModel::startThingToDo,
                onThingToDoPause = viewModel::pauseThingToDo,
            )
        }
    }
}

@Composable
fun WelcomeScreen(
    uiState: WelcomeUiState,
    activeThingToDo: ActiveThingToDo?,
    onThingToDoComplete: (ThingToDo) -> Unit,
    onThingToDoStart: (ThingToDo) -> Unit,
    onThingToDoPause: (ActiveThingToDo) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        WelcomeContent(
            activeThingToDo,
            onThingToDoComplete = onThingToDoComplete,
            onThingToDoStart = onThingToDoStart,
            onThingToDoPause = onThingToDoPause,
            Modifier
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding),
        )
    }
}
