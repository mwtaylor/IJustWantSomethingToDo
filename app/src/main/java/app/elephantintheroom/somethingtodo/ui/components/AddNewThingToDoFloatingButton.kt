package app.elephantintheroom.somethingtodo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun AddNewThingToDoFloatingButton(
    onAddNewThingToDo: () -> Unit
) {
    FloatingActionButton(onClick = { onAddNewThingToDo.invoke() }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}