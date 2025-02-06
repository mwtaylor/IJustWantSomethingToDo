package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.R

@Composable
fun NewThingToDoButton(
    includeText: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (includeText) {
        ExtendedFloatingActionButton(
            onClick = onClick,
            icon = { NewThingToDoButtonIcon() },
            text = { Text(text = stringResource(R.string.addThingToDo)) },
            modifier = modifier,
        )
    } else {
        FloatingActionButton(
            onClick = onClick,
            modifier = modifier,
        ) {
            NewThingToDoButtonIcon()
        }
    }
}

@Composable
fun NewThingToDoButtonIcon() {
    Icon(Icons.Default.Add, stringResource(R.string.addThingToDo))
}
