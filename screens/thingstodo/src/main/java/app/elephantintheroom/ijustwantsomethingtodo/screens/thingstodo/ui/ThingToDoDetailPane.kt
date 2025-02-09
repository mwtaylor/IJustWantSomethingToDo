package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ExistingThingToDoListItem

@Composable
fun ThingToDoDetailPane(
    thingToDoListItem: ExistingThingToDoListItem,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Text(text = thingToDoListItem.thingToDo.name)
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun ThingToDoDetailPanePreview() {
    ThingToDoDetailPane(
        ExistingThingToDoListItem(
            ThingToDo(1, "fix bugs"),
            null,
        )
    )
}
