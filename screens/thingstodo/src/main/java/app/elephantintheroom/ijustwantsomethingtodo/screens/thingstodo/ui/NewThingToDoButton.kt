package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.R

@Composable
fun NewThingToDoButton(
    includeText: Boolean,
) {
    if (includeText) {
        ExtendedFloatingActionButton(
            onClick = {},
            icon = { NewThingToDoButtonIcon() },
            text = { Text(text = stringResource(R.string.addThingToDo)) },
        )
    } else {
        FloatingActionButton(
            onClick = {},
        ) {
            NewThingToDoButtonIcon()
        }
    }
}

@Composable
fun NewThingToDoButtonIcon() {
    Icon(Icons.Filled.Add, stringResource(R.string.addThingToDo))
}
