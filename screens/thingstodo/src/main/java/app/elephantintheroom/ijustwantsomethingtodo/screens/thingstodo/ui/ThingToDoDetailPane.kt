package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.ijustwantsomethingtodo.core.time.atStartOfDay
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ExistingThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.InactiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoWithTimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.ui.common.LocalClock
import app.elephantintheroom.ijustwantsomethingtodo.ui.common.UpdatingDuration
import app.elephantintheroom.ijustwantsomethingtodo.ui.thingtodo.PauseThingToDoButton
import app.elephantintheroom.ijustwantsomethingtodo.ui.thingtodo.StartThingToDoButton
import java.time.Instant

@Composable
fun ThingToDoDetailPane(
    thingToDoWithTimeSpent: ThingToDoWithTimeSpent,
    onStart: () -> Unit,
    onPause: (TimeSpent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier,
    ) {
        Text(
            text = thingToDoWithTimeSpent.thingToDo.name,
            style = MaterialTheme.typography.headlineMedium,
        )
        if (thingToDoWithTimeSpent is ActiveThingToDo) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PauseThingToDoButton(
                    thingToDoWithTimeSpent.thingToDo,
                    { onPause(thingToDoWithTimeSpent.activeTimeSpent) },
                )

                val clock = LocalClock.current
                UpdatingDuration {
                    val now = Instant.now(clock)
                    thingToDoWithTimeSpent.activeDuration(now)
                }
            }
        } else {
            StartThingToDoButton(
                thingToDoWithTimeSpent.thingToDo,
                onStart,
            )
        }
        TotalTimeSpent(
            thingToDoWithTimeSpent,
            modifier = Modifier.requiredWidth(IntrinsicSize.Max),
        )
    }
}

@Composable
fun TotalTimeSpent(
    thingToDoWithTimeSpent: ThingToDoWithTimeSpent,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Total time spent: "
            )
            UpdatingDuration(thingToDoWithTimeSpent::totalConcludedDuration)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Time spent today: "
            )
            Text(
                text = "00:00:00"
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 200,
)
@Composable
fun ThingToDoDetailPanePreview() {
    ThingToDoDetailPane(
        InactiveThingToDo(
            ExistingThingToDo(1, "fix bugs"),
            emptyList(),
        ),
        {},
        {},
    )
}

@Preview(
    showBackground = true,
    widthDp = 200,
)
@Composable
fun ActiveThingToDoDetailPanePreview() {
    ThingToDoDetailPane(
        ActiveThingToDo(
            ExistingThingToDo(1, "fix bugs"),
            TimeSpent(1, 1, Instant.now().atStartOfDay(), null),
            emptyList(),
        ),
        {},
        {},
    )
}
