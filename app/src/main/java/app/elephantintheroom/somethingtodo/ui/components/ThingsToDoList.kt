package app.elephantintheroom.somethingtodo.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.TimeSpent
import app.elephantintheroom.somethingtodo.ui.ThingToDoWithTimeSpent
import java.time.Duration
import java.time.temporal.ChronoUnit

@Composable
fun ThingsToDoList(
    thingsToDo: List<ThingToDoWithTimeSpent>,
    modifier: Modifier = Modifier,
    startSpendingTime: (ThingToDo) -> Unit,
    stopSpendingTime: (ThingToDo, TimeSpent) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(thingsToDo.toList()) {
            ThingToDoItem(
                it.thingToDo,
                it.activeTimeSpent,
                it.recentTimeSpent.getOrDefault(ChronoUnit.DAYS to 0, Duration.ZERO),
                { startSpendingTime(it.thingToDo) },
                { timeSpent: TimeSpent -> stopSpendingTime(it.thingToDo, timeSpent) },
            )
        }
    }
}