package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ThingToDoViewModelProvider
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.navigation.ThingsToDoRoute

fun NavGraphBuilder.thingsToDoScreen(navController: NavController) {
    composable<ThingsToDoRoute> { backStackEntry ->
        val baseEntry = remember(backStackEntry) {
            navController.getBackStackEntry(ThingsToDoRoute)
        }

        val viewModel: ThingsToDoViewModel = viewModel(
            baseEntry,
            factory = ThingToDoViewModelProvider.factory,
        )

        ThingsToDoScreen(viewModel)
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ThingsToDoScreen(
    viewModel: ThingsToDoViewModel,
    modifier: Modifier = Modifier,
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<ThingToDoListItem>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    val uiState: ThingsToDoUiState by viewModel.uiState.collectAsStateWithLifecycle()

    ThingsToDoListDetail(
        navigator.scaffoldDirective,
        navigator.scaffoldValue,
        uiState.thingsToDo.map { ThingToDoListItem(it.id, it.name) },
        navigator.currentDestination?.content,
        onItemClick = {
            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, it)
        },
        onNewThingToDoClick = {
            navigator.navigateTo(
                ListDetailPaneScaffoldRole.Extra,
                navigator.currentDestination?.content,
            )
        },
        modifier,
    )
}
