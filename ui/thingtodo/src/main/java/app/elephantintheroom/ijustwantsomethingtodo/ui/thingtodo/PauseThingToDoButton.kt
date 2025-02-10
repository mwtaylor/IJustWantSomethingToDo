package app.elephantintheroom.ijustwantsomethingtodo.ui.thingtodo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo

@Composable
fun PauseThingToDoButton(
    thingToDo: ThingToDo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            Icons.Default.Pause,
            stringResource(R.string.ui_thingtodo_stopSpendingTime, thingToDo.name)
        )
    }
}
