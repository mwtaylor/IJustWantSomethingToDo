package app.elephantintheroom.ijustwantsomethingtodo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.ijustwantsomethingtodo.ui.theme.IJustWantSomethingToDoTheme

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column {
        Greeting(
            name = "Android",
        )
        RunnableTaskList(
            tasks = listOf("Work", "Play"),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    IJustWantSomethingToDoTheme {
        MainScreen()
    }
}
