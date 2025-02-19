package app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ExistingThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.R
import app.elephantintheroom.ijustwantsomethingtodo.ui.thingtodo.ThingToDoCard
import java.time.Instant

@Composable
fun WelcomeContent(
    activeThingToDo: ActiveThingToDo?,
    onThingToDoComplete: (ThingToDo) -> Unit,
    onThingToDoStart: (ThingToDo) -> Unit,
    onThingToDoPause: (ActiveThingToDo) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(text = stringResource(R.string.welcome))
        if (activeThingToDo != null) {
            ThingToDoCard(
                activeThingToDo,
                onComplete = { onThingToDoComplete(activeThingToDo.thingToDo) },
                onStart = { onThingToDoStart(activeThingToDo.thingToDo) },
                onPause = { onThingToDoPause(activeThingToDo) },
            )
        }
    }
}

@Preview(
    showBackground = true,
    heightDp = 300,
    widthDp = 300,
)
@Composable
fun WelcomeContentPreview() {
    WelcomeContent(
        ActiveThingToDo(
            ExistingThingToDo(id = 1, "fix bugs"),
            TimeSpent(id = 1, thingToDoId = 1, Instant.EPOCH, null),
            emptyList(),
        ),
        {},
        {},
        {},
    )
}
