package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoWithTimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ThingToDoViewModelProvider
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ExistingThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.NewThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.navigation.ThingsToDoRoute
import app.elephantintheroom.ijustwantsomethingtodo.ui.common.LocalClock
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
        val uiState: ThingsToDoUiState by viewModel.uiState.collectAsStateWithLifecycle()

        CompositionLocalProvider(LocalClock provides viewModel.clock) {
            ThingsToDoScreen(
                uiState,
                viewModel::addThingToDo,
                viewModel::startSpendingTime,
                viewModel::stopSpendingTime,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ThingsToDoScreen(
    uiState: ThingsToDoUiState,
    onAddNewThingToDo: (ThingToDo, (ThingToDoWithTimeSpent) -> Unit) -> Unit,
    onStartSpendingTime: (ThingToDo) -> Unit,
    onStopSpendingTime: (TimeSpent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<ThingToDoListItem>()
    val coroutineScope = rememberCoroutineScope()

    BackHandler(navigator.canNavigateBack()) {
        coroutineScope.launch {
            navigator.navigateBack()
        }
    }

    ThingsToDoListDetail(
        navigator.scaffoldDirective,
        navigator.scaffoldValue,
        uiState.thingsToDo.map { ExistingThingToDoListItem(it) },
        navigator.currentDestination?.content,
        onSelectItem = {
            coroutineScope.launch {
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, it)
            }
        },
        onBeginNewThingToDo = {
            coroutineScope.launch {
                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, NewThingToDoListItem)
            }
        },
        onAddNewThingToDo = { thingToDo ->
            val onComplete: (ThingToDoWithTimeSpent) -> Unit = { newThingToDo ->
                coroutineScope.launch {
                    navigator.navigateBack(BackNavigationBehavior.PopLatest)
                    navigator.navigateTo(
                        ListDetailPaneScaffoldRole.Detail,
                        ExistingThingToDoListItem(newThingToDo),
                    )
                }
            }
            onAddNewThingToDo(thingToDo, onComplete)
        },
        onCancelNewThingToDo = {
            coroutineScope.launch {
                navigator.navigateBack(BackNavigationBehavior.PopLatest)
            }
        },
        onStartSpendingTime = onStartSpendingTime,
        onStopSpendingTime = onStopSpendingTime,
        modifier,
    )
}
