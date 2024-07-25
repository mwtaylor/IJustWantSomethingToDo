package app.elephantintheroom.somethingtodo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import app.elephantintheroom.somethingtodo.R
import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.TimeSpent
import app.elephantintheroom.somethingtodo.ui.ThingToDoWithTimeSpent
import app.elephantintheroom.somethingtodo.ui.components.StopSpendingTimeButton
import app.elephantintheroom.somethingtodo.ui.components.ThingsToDoList
import app.elephantintheroom.somethingtodo.ui.components.UpdatingDuration
import java.time.Duration
import java.time.Instant

@Composable
fun ThingsToDoScreen(
    thingsToDo: List<ThingToDoWithTimeSpent>,
    modifier: Modifier = Modifier,
    activeTimeSpent: Pair<ThingToDo, TimeSpent>? = null,
    startSpendingTime: (ThingToDo) -> Unit,
    stopSpendingTime: (ThingToDo, TimeSpent) -> Unit,
) {
    Column(modifier) {
        if (activeTimeSpent != null) {
            Row(
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding),
                    horizontal = dimensionResource(R.dimen.double_padding)
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StopSpendingTimeButton(activeTimeSpent.first) {
                    stopSpendingTime(activeTimeSpent.first, activeTimeSpent.second)
                }
                Text(text = activeTimeSpent.first.name)
                Spacer(modifier = Modifier.weight(1.0F))
                UpdatingDuration { Duration.between(activeTimeSpent.second.started, Instant.now()) }
            }
        }
        if (thingsToDo.isEmpty()) {
            Text(text = "Nothing available to do right now")
        } else {
            ThingsToDoList(
                thingsToDo,
                startSpendingTime = startSpendingTime,
                stopSpendingTime = stopSpendingTime
            )
        }
    }
}
