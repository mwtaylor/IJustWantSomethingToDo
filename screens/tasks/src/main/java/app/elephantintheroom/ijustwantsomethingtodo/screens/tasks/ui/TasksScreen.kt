package app.elephantintheroom.ijustwantsomethingtodo.screens.tasks.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.elephantintheroom.ijustwantsomethingtodo.screens.tasks.navigation.TasksRoute

fun NavGraphBuilder.tasksScreen() {
    composable<TasksRoute> {
        TasksScreen()
    }
}

@Composable
fun TasksScreen(modifier: Modifier = Modifier) {
    Text("Tasks")
}
