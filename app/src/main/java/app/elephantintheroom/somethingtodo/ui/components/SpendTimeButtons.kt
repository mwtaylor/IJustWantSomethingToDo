package app.elephantintheroom.somethingtodo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.elephantintheroom.somethingtodo.R
import app.elephantintheroom.somethingtodo.data.ThingToDo

@Composable
fun StartSpendingTimeButton(
    thingToDo: ThingToDo,
    startSpendingTime: () -> Unit,
) {
    IconButton(onClick = startSpendingTime) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = stringResource(R.string.start_spending_time_icon_description, thingToDo.name)
        )
    }
}

@Composable
fun StopSpendingTimeButton(
    thingToDo: ThingToDo,
    stopSpendingTime: () -> Unit,
) {
    IconButton(onClick = { stopSpendingTime() }) {
        Icon(
            imageVector = Icons.Filled.Stop,
            contentDescription = stringResource(R.string.stop_spending_time_icon_description, thingToDo.name)
        )
    }
}
