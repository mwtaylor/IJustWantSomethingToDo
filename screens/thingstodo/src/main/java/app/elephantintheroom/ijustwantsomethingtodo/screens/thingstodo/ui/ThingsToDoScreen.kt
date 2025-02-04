package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.navigation.ThingsToDoRoute

fun NavGraphBuilder.thingsToDoScreen() {
    composable<ThingsToDoRoute> {
        ThingsToDoScreen()
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ThingsToDoScreen(modifier: Modifier = Modifier) {
    val navigator = rememberListDetailPaneScaffoldNavigator<ThingToDoListItem>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                ThingsToDoListPane(
                    thingsToDo = listOf(
                        ThingToDoListItem(1, "Work"),
                        ThingToDoListItem(2, "Play"),
                    ),
                    onItemClick = { item ->
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, item)
                    },
                )
            }
        },
        detailPane = {
            AnimatedPane {
                // Show the detail pane content if selected item is available
                navigator.currentDestination?.content?.let {
                    ThingToDoDetailPane(it)
                }
            }
        },
        modifier = modifier,
    )
}

@Preview(
    showBackground = true,
)
@Composable
fun ThingsToDoScreenPreview() {
    ThingsToDoScreen()
}
