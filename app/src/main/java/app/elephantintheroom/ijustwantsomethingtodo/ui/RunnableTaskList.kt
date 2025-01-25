package app.elephantintheroom.ijustwantsomethingtodo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RunnableTaskList(
    tasks: List<String>,
    startTask: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        for (task in tasks) {
            Row {
                Text(task)
                Button(onClick = { startTask(task) }) {
                    Text("Start")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RunnableTaskListPreview() {
    RunnableTaskList(
        listOf("Work", "Play"),
        {},
    )
}
