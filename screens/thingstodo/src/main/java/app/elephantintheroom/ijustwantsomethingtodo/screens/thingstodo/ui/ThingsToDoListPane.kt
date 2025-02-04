package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ThingToDoListItem

@Composable
fun ThingsToDoListPane(
    thingsToDo: List<ThingToDoListItem>,
    onItemClick: (ThingToDoListItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        LazyColumn {
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
)
@Composable
fun ThingsToDoListPanePreview() {
    ThingsToDoListPane(
        thingsToDo = listOf(
            ThingToDoListItem(1, "fix bugs"),
            ThingToDoListItem(2, "submit code review"),
            ThingToDoListItem(3, "merge code"),
        ),
        onItemClick = {},
    )
}
