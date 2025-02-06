package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ThingToDoViewModelProvider
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.navigation.ThingsToDoRoute
import kotlinx.coroutines.launch

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
    val coroutineScope = rememberCoroutineScope()

    BackHandler(navigator.canNavigateBack()) {
        coroutineScope.launch {
            navigator.navigateBack()
        }
    }

    val uiState: ThingsToDoUiState by viewModel.uiState.collectAsStateWithLifecycle()

    ThingsToDoListDetail(
        navigator.scaffoldDirective,
        navigator.scaffoldValue,
        uiState.thingsToDo.map { ThingToDoListItem(it.id, it.name) },
        navigator.currentDestination?.content,
        onSelectItem = {
            coroutineScope.launch {
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, it)
            }
        },
        onBeginNewThingToDo = {
            coroutineScope.launch {
                navigator.navigateTo(
                    ListDetailPaneScaffoldRole.Extra,
                    navigator.currentDestination?.content,
                )
            }
        },
        onAddNewThingToDo = { thingToDo ->
            val onComplete: (ThingToDo) -> Unit = { newThingToDo ->
                coroutineScope.launch {
                    navigator.navigateBack(BackNavigationBehavior.PopUntilScaffoldValueChange)
                    navigator.navigateTo(
                        ListDetailPaneScaffoldRole.Detail,
                        ThingToDoListItem(newThingToDo.id, newThingToDo.name),
                    )
                }
            }
            viewModel.addThingToDo(thingToDo, onComplete)
        },
        onCancelNewThingToDo = {
            coroutineScope.launch {
                if (navigator.canNavigateBack())
                    navigator.navigateBack()
            }
        },
        modifier,
    )
}
