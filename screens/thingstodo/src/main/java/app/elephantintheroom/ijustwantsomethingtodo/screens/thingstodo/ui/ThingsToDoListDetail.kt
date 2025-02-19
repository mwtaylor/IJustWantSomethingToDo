package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.PaneScaffoldDirective
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ExistingThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.InactiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ThingToDoViewModelProvider
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
                    is ExistingThingToDoListItem -> {
                        val viewModelStoreOwner = LocalViewModelStoreOwner.current
                        val defaultExtras =
                            if (viewModelStoreOwner is HasDefaultViewModelProviderFactory) {
                                viewModelStoreOwner.defaultViewModelCreationExtras
                            } else {
                                CreationExtras.Empty
                            }
                        val viewModel: ThingToDoViewModel = viewModel(
                            key = "ThingToDo${content.thingToDoWithTimeSpent.thingToDo.id}",
                            factory = ThingToDoViewModelProvider.factory,
                            extras = MutableCreationExtras(defaultExtras).apply {
                                set(
                                    ThingToDoViewModelProvider.THING_TO_DO_KEY,
                                    content.thingToDoWithTimeSpent,
                                )
                            },
                        )
                        val uiState: ThingToDoUiState by viewModel.uiState.collectAsStateWithLifecycle()

                        ThingToDoDetailPane(
                            uiState.thingToDo,
                            viewModel::startSpendingTime,
                            viewModel::stopSpendingTime,
                        )
                    }
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
                    ExistingThingToDo(1, "fix bugs"),
                    emptyList(),
                ),
            ),
            ExistingThingToDoListItem(
                InactiveThingToDo(
                    ExistingThingToDo(2, "submit code review"),
                    emptyList(),
                ),
            ),
        ),
        ExistingThingToDoListItem(
            InactiveThingToDo(
                ExistingThingToDo(1, "fix bugs"),
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
