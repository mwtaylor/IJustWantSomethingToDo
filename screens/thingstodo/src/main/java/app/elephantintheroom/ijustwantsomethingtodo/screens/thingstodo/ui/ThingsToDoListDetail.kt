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
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.InactiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ExistingThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.NewThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ThingToDoListItem

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ThingsToDoListDetail(
    scaffoldDirective: PaneScaffoldDirective,
    scaffoldValue: ThreePaneScaffoldValue,
    items: List<ExistingThingToDoListItem>,
    content: ThingToDoListItem?,
    onSelectItem: (ThingToDoListItem) -> Unit,
    onBeginNewThingToDo: () -> Unit,
    onAddNewThingToDo: (ThingToDo) -> Unit,
    onCancelNewThingToDo: () -> Unit,
    onStartSpendingTime: (ThingToDo) -> Unit,
    onStopSpendingTime: (TimeSpent) -> Unit,
    modifier: Modifier = Modifier,
) {
    ListDetailPaneScaffold(
        directive = scaffoldDirective,
        value = scaffoldValue,
        listPane = {
            AnimatedPane {
                ThingsToDoListPane(
                    thingToDoListItems = items,
                    expandFloatingAddButton = scaffoldDirective.maxHorizontalPartitions > 1,
                    onItemClick = onSelectItem,
                    onNewThingToDoClick = onBeginNewThingToDo,
                    onStartSpendingTime = { onStartSpendingTime(it.thingToDoWithTimeSpent.thingToDo) },
                    onStopSpendingTime = {
                        if (it.thingToDoWithTimeSpent is ActiveThingToDo) {
                            onStopSpendingTime(it.thingToDoWithTimeSpent.activeTimeSpent)
                        }
                    },
                )
            }
        },
        detailPane = {
            AnimatedPane {
                when (content) {
                    is ExistingThingToDoListItem -> ThingToDoDetailPane(content.thingToDoWithTimeSpent)
                    is NewThingToDoListItem -> NewThingToDoPane(
                        save = onAddNewThingToDo,
                        cancel = onCancelNewThingToDo,
                    )
                    else -> {}
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
        ThreePaneScaffoldValue(
            PaneAdaptedValue.Expanded,
            PaneAdaptedValue.Expanded,
            PaneAdaptedValue.Hidden,
        ),
        listOf(
            ExistingThingToDoListItem(
                InactiveThingToDo(
                    ThingToDo(1, "fix bugs"),
                    emptyList(),
                ),
            ),
            ExistingThingToDoListItem(
                InactiveThingToDo(
                    ThingToDo(2, "submit code review"),
                    emptyList(),
                ),
            ),
        ),
        ExistingThingToDoListItem(
            InactiveThingToDo(
                ThingToDo(1, "fix bugs"),
                emptyList()
            ),
        ),
        {},
        {},
        {},
        {},
        {},
        {},
    )
}
