package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.PaneScaffoldDirective
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ThingToDoListItem

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ThingsToDoListDetail(
    scaffoldDirective: PaneScaffoldDirective,
    scaffoldValue: ThreePaneScaffoldValue,
    items: List<ThingToDoListItem>,
    content: ThingToDoListItem?,
    onItemClick: (ThingToDoListItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    ListDetailPaneScaffold(
        directive = scaffoldDirective,
        value = scaffoldValue,
        listPane = {
            AnimatedPane {
                ThingsToDoListPane(
                    thingsToDo = items,
                    expandFloatingAddButton = scaffoldDirective.maxHorizontalPartitions > 1,
                    onItemClick = onItemClick,
                )
            }
        },
        detailPane = {
            AnimatedPane {
                content?.let {
                    ThingToDoDetailPane(it)
                }
            }
        },
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Preview(
    showBackground = true,
    widthDp = 600,
    heightDp = 400,
)
@Composable
fun ThingToDoListDetailPreview() {
    ThingsToDoListDetail(
        PaneScaffoldDirective.Default,
        ThreePaneScaffoldValue(PaneAdaptedValue.Expanded, PaneAdaptedValue.Expanded, PaneAdaptedValue.Hidden),
        listOf(
            ThingToDoListItem(1, "fix bugs"),
            ThingToDoListItem(2, "submit code review"),
        ),
        ThingToDoListItem(1, "fix bugs"),
        {},
    )
}
