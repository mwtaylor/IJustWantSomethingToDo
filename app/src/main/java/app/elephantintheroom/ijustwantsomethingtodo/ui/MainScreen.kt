package app.elephantintheroom.ijustwantsomethingtodo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.ijustwantsomethingtodo.ui.theme.IJustWantSomethingToDoTheme

@Composable
fun MainScreen(uiState: AppUiState, modifier: Modifier = Modifier) {
    Column {
        Greeting(
            name = uiState.name,
        )
        RunnableTaskList(
            tasks = uiState.tasks,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    IJustWantSomethingToDoTheme {
        MainScreen(
            AppUiState(
                "Android",
                listOf("Work", "Play")
            )
        )
    }
}
