package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ExistingThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ThingToDoListItem

@Composable
fun ThingsToDoListPane(
    thingsToDo: List<ExistingThingToDoListItem>,
    onItemClick: (ThingToDoListItem) -> Unit,
    onNewThingToDoClick: () -> Unit,
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
                for (thingToDo in thingsToDo) {
                    ListItem(
                        headlineContent = {
                            Text(text = thingToDo.name)
                        },
                        modifier = Modifier
                            .clickable {
                                onItemClick(thingToDo)
                            },
                    )
                }
            }
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
        thingsToDo = listOf(
            ExistingThingToDoListItem(1, "fix bugs"),
            ExistingThingToDoListItem(2, "submit code review"),
            ExistingThingToDoListItem(3, "merge code"),
        ),
        {},
        {},
    )
}
