package app.elephantintheroom.ijustwantsomethingtodo.ui.thingtodo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.elephantintheroom.ijustwantsomethingtodo.core.time.atStartOfDay
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ActiveThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDoWithTimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.data.model.TimeSpent
import app.elephantintheroom.ijustwantsomethingtodo.ui.common.LocalClock
import app.elephantintheroom.ijustwantsomethingtodo.ui.common.UpdatingDuration
import java.time.Instant

@Composable
fun ThingToDoCard(
    thingToDoWithTimeSpent: ThingToDoWithTimeSpent,
    onComplete: () -> Unit,
    onStart: () -> Unit,
    onPause: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                CompleteThingToDoButton(
                    thingToDoWithTimeSpent.thingToDo,
                    onComplete,
                )
                Text(text = thingToDoWithTimeSpent.thingToDo.name)
            }

            if (thingToDoWithTimeSpent is ActiveThingToDo) {
                PauseThingToDoButton(
                    thingToDoWithTimeSpent.thingToDo,
                    onPause,
                )
            } else {
                StartThingToDoButton(
                    thingToDoWithTimeSpent.thingToDo,
                    onStart,
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth(),
        ) {
            UpdatingDuration(thingToDoWithTimeSpent::totalConcludedDuration)
            if (thingToDoWithTimeSpent is ActiveThingToDo) {
                val clock = LocalClock.current
                UpdatingDuration {
                    val now = Instant.now(clock)
                    thingToDoWithTimeSpent.activeDuration(now)
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun ThingToDoCardPreview() {
    ThingToDoCard(
        ActiveThingToDo(
            ThingToDo(
                id = 1,
                name = "fix bugs",
            ),
            TimeSpent(
                id = 1,
                thingToDoId = 1,
                started = Instant.now().atStartOfDay(),
                ended = null,
            ),
            listOf(
                TimeSpent(
                    id = 2,
                    thingToDoId = 1,
                    started = Instant.EPOCH,
                    ended = Instant.ofEpochSecond(400),
                )
            ),
        ),
        {},
        {},
        {},
    )
}
