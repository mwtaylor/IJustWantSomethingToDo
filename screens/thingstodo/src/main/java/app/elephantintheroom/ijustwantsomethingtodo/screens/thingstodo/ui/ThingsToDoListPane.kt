package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
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
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.R
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ExistingThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ThingToDoListItem

@Composable
fun ThingsToDoListPane(
    thingToDoListItems: List<ExistingThingToDoListItem>,
    onItemClick: (ThingToDoListItem) -> Unit,
    onNewThingToDoClick: () -> Unit,
    onStartSpendingTime: (ExistingThingToDoListItem) -> Unit,
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
                            ThingToDoInList(
                                thingToDoListItem,
                                onStartSpendingTime,
                            )
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
    onStartSpendingTime: (ExistingThingToDoListItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { onStartSpendingTime(thingToDoListItem) }
        ) {
            Icon(
                Icons.Default.PlayArrow,
                stringResource(R.string.startSpendingTime, thingToDoListItem.name)
            )
        }
        Text(text = thingToDoListItem.name)
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
            ExistingThingToDoListItem(1, "fix bugs"),
            ExistingThingToDoListItem(2, "submit code review"),
            ExistingThingToDoListItem(3, "merge code"),
        ),
        {},
        {},
        {},
    )
}
