package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ExistingThingToDoListItem
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.model.ThingToDoListItem

@Composable
fun ThingToDoDetailPane(
    thingToDo: ExistingThingToDoListItem,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Text(text = thingToDo.name)
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun ThingToDoDetailPanePreview() {
    ThingToDoDetailPane(
        ExistingThingToDoListItem(1, "fix bugs")
    )
}
