package app.elephantintheroom.ijustwantsomethingtodo.ui.thingtodo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo

@Composable
fun CompleteThingToDoButton(
    thingToDo: ThingToDo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Icon(
            Icons.Default.CheckCircle,
            stringResource(R.string.ui_thingtodo_completeThingToDo, thingToDo.name)
        )
    }
}
