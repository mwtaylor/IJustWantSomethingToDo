package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ExistingThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.InactiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ExistingThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.ui.thingtodo.CompleteThingToDoButton
import app.elephantintheroom.ijustwantsomethingtodo.ui.thingtodo.PauseThingToDoButton
import app.elephantintheroom.ijustwantsomethingtodo.ui.thingtodo.StartThingToDoButton
import java.time.Instant

@Composable
fun ThingsToDoListPane(
    thingToDoListItems: List<ExistingThingToDoListItem>,
    onItemClick: (ThingToDoListItem) -> Unit,
    onNewThingToDoClick: () -> Unit,
    onStartSpendingTime: (ExistingThingToDoListItem) -> Unit,
    onStopSpendingTime: (ExistingThingToDoListItem) -> Unit,
    modifier: Modifier = Modifier,
    expandFloatingAddButton: Boolean = false,
) {
    Scaffold(
        floatingActionButton = {
            NewThingToDoButton(
                includeText = expandFloatingAddButton,
                onClick = onNewThingToDoClick
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(innerPadding),
            contentPadding = innerPadding
        ) {
            item {
                for (thingToDoListItem in thingToDoListItems) {
                    ListItem(
                        headlineContent = {
                            if (thingToDoListItem.thingToDoWithTimeSpent is ActiveThingToDo) {
                                RunningThingToDoInList(
                                    thingToDoListItem,
                                    onStopSpendingTime,
                                )
                            } else {
                                PausedThingToDoInList(
                                    thingToDoListItem,
                                    onStartSpendingTime,
                                )
                            }
                        },
                        modifier = Modifier
                            .clickable {
                                onItemClick(thingToDoListItem)
                            },
                    )
                }
            }
        }
    }
}

@Composable
fun ThingToDoInList(
    thingToDoListItem: ExistingThingToDoListItem,
    modifier: Modifier = Modifier,
    iconButton: @Composable () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CompleteThingToDoButton(
            thingToDoListItem.thingToDoWithTimeSpent.thingToDo,
            {},
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = thingToDoListItem.thingToDoWithTimeSpent.thingToDo.name)
            iconButton()
        }
    }
}

@Composable
fun PausedThingToDoInList(
    thingToDoListItem: ExistingThingToDoListItem,
    onStartSpendingTime: (ExistingThingToDoListItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    ThingToDoInList(
        thingToDoListItem,
        modifier,
    ) {
        StartThingToDoButton(
            thingToDoListItem.thingToDoWithTimeSpent.thingToDo,
            onClick = { onStartSpendingTime(thingToDoListItem) }
        )
    }
}

@Composable
fun RunningThingToDoInList(
    thingToDoListItem: ExistingThingToDoListItem,
    onStopSpendingTime: (ExistingThingToDoListItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    ThingToDoInList(
        thingToDoListItem,
        modifier,
    ) {
        PauseThingToDoButton(
            thingToDoListItem.thingToDoWithTimeSpent.thingToDo,
            onClick = { onStopSpendingTime(thingToDoListItem) },
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 300,
    heightDp = 300,
)
@Composable
fun ThingsToDoListPanePreview() {
    ThingsToDoListPane(
        thingToDoListItems = listOf(
            ExistingThingToDoListItem(
                InactiveThingToDo(
                    ExistingThingToDo(1, "fix bugs"),
                    listOf(TimeSpent(1, 1, Instant.EPOCH, Instant.now())),
                ),
            ),
            ExistingThingToDoListItem(
                ActiveThingToDo(
                    ExistingThingToDo(2, "submit code review"),
                    TimeSpent(2, 2, Instant.EPOCH, null),
                    emptyList(),
                ),
            ),
            ExistingThingToDoListItem(
                InactiveThingToDo(
                    ExistingThingToDo(3, "merge code"),
                    emptyList(),
                ),
            ),
        ),
        {},
        {},
        {},
        {},
    )
}
