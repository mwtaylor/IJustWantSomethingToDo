package app.elephantintheroom.somethingtodo.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.somethingtodo.R
import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.TimeSpent
import app.elephantintheroom.somethingtodo.data.previewThingsToDo
import app.elephantintheroom.somethingtodo.data.previewTimeSpent
import app.elephantintheroom.somethingtodo.ui.theme.IJustWantSomethingToDoTheme
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit

@Composable
fun ThingToDoItem(
    thingToDo: ThingToDo,
    activeTimeSpent: TimeSpent?,
    totalTimeSpentToday: Duration,
    startSpendingTime: () -> Unit,
    stopSpendingTime: (TimeSpent) -> Unit,
    startExpanded: Boolean = false,
) {
    var expanded by remember { mutableStateOf(startExpanded) }
    val color by animateColorAsState(
        targetValue = if (activeTimeSpent != null) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.secondaryContainer
        },
        label = "Animate Thing To Do Card Color"
    )
    Card(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding))
            .background(color),
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = if (activeTimeSpent != null) {
                MaterialTheme.colorScheme.onPrimaryContainer
            } else {
                MaterialTheme.colorScheme.onSecondaryContainer
            },
        )
    ) {
        Column(
            modifier = Modifier.animateContentSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (activeTimeSpent == null) {
                    StartSpendingTimeButton(thingToDo, startSpendingTime)
                } else {
                    StopSpendingTimeButton(thingToDo) { stopSpendingTime(activeTimeSpent) }
                }
                Text(text = thingToDo.name)
                Spacer(Modifier.weight(1.0F))
                IconButton(onClick = { expanded = !expanded }) {
                    if (expanded) {
                        Icon(
                            imageVector = Icons.Filled.ExpandLess,
                            contentDescription =
                            stringResource(R.string.collapse_thing_to_do, thingToDo.name)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.ExpandMore,
                            contentDescription =
                            stringResource(R.string.expand_thing_to_do, thingToDo.name)
                        )
                    }
                }
            }
            if (expanded) {
                // How to recompose when going to next day but otherwise view model doesn't change?
                val startOfDay = Instant.now().truncatedTo(ChronoUnit.DAYS)
                val totalTimeSpentTodayIncludingActive = {
                    totalTimeSpentToday +
                            if (activeTimeSpent != null) {
                                val started = if (activeTimeSpent.started < startOfDay) {
                                    startOfDay
                                } else {
                                    activeTimeSpent.started
                                }
                                Duration.between(activeTimeSpent.started, Instant.now())
                            } else {
                                Duration.ZERO
                            }
                }
                Row(
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding))
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    UpdatingDuration(totalTimeSpentTodayIncludingActive)
                }
            }
        }
    }
}

@Preview
@Composable
fun ExpandedThingToDoPreview() {
    val thingToDo = previewThingsToDo.single { it.name == "Work" }
    val activeTimeSpent = previewTimeSpent.single { it.thingToDoId == thingToDo.id && it.finished == null }
    IJustWantSomethingToDoTheme {
        ThingToDoItem(
            thingToDo = thingToDo,
            activeTimeSpent = activeTimeSpent,
            totalTimeSpentToday = Duration.ofMinutes(3),
            startSpendingTime = {},
            stopSpendingTime = {},
            startExpanded = true,
        )
    }
}
