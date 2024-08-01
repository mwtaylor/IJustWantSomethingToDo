package app.elephantintheroom.somethingtodo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import app.elephantintheroom.somethingtodo.data.ThingToDo

@Composable
fun AddThingToDoScreen(
    modifier: Modifier = Modifier,
    onAddThingToDo: (ThingToDo) -> Unit,
    onCancelAddingThingToDo: () -> Unit,
) {
    Column(modifier) {
        Text(text = "Add something new to do")

        var name by remember { mutableStateOf("") }
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Label") },
        )

        Button(
            onClick = { onAddThingToDo(ThingToDo(name = name)) },
        ) {
            Text(text = "Add")
        }

        Button(
            onClick = { onCancelAddingThingToDo() },
        ) {
            Text(text = "Cancel")
        }
    }
}
