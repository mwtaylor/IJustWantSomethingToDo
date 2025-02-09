package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.R
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ExistingThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ThingToDoListItem
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
                            if (thingToDoListItem.activeTimeSpent?.let {
                                it.ended == null
                            } == true) {
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
        IconButton(
            onClick = {  }
        ) {
            Icon(
                Icons.Default.CheckCircle,
                stringResource(R.string.completeThingToDo, thingToDoListItem.thingToDo.name)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = thingToDoListItem.thingToDo.name)
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
        IconButton(
            onClick = { onStartSpendingTime(thingToDoListItem) }
        ) {
            Icon(
                Icons.Default.PlayArrow,
                stringResource(R.string.startSpendingTime, thingToDoListItem.thingToDo.name)
            )
        }
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
        IconButton(
            onClick = { onStopSpendingTime(thingToDoListItem) }
        ) {
            Icon(
                Icons.Default.Pause,
                stringResource(R.string.stopSpendingTime, thingToDoListItem.thingToDo.name)
            )
        }
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
                ThingToDo(1, "fix bugs"),
                TimeSpent(1, 1, Instant.EPOCH, Instant.now()),
            ),
            ExistingThingToDoListItem(
                ThingToDo(2, "submit code review"),
                TimeSpent(2, 2, Instant.EPOCH, null),
            ),
            ExistingThingToDoListItem(
                ThingToDo(3, "merge code"),
                null,
            ),
        ),
        {},
        {},
        {},
        {},
    )
}
