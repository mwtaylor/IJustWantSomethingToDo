package app.elephantintheroom.ijustwantsomethingtodo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RunnableTaskList(
    tasks: List<String>,
    modifier: Modifier = Modifier,
) {
    Column {
        for (task in tasks) {
            Text(task)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RunnableTaskListPreview() {
    RunnableTaskList(
        listOf("Work", "Play"),
    )
}
