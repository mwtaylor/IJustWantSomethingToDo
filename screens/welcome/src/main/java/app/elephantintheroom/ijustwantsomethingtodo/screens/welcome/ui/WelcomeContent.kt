package app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoWithActiveTimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.R
import app.elephantintheroom.ijustwantsomethingtodo.ui.thingtodo.ThingToDoCard
import java.time.Instant

@Composable
fun WelcomeContent(
    activeThingToDo: ThingToDoWithActiveTimeSpent?,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(text = stringResource(R.string.welcome))
        if (activeThingToDo != null) {
            ThingToDoCard(
                activeThingToDo.toOptionalActiveTimeSpent(),
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
        ThingToDoWithActiveTimeSpent(
            ThingToDo(id = 1, "fix bugs"),
            TimeSpent(id = 1, thingToDoId = 1, Instant.EPOCH, null)
        )
    )
}
