package app.elephantintheroom.somethingtodo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddThingToDoScreen(
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(text = "Add something new to do")
    }
}
