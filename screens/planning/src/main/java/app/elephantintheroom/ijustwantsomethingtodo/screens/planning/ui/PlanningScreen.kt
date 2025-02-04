package app.elephantintheroom.ijustwantsomethingtodo.screens.planning.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.elephantintheroom.ijustwantsomethingtodo.screens.planning.navigation.PlanningRoute

fun NavGraphBuilder.planningScreen() {
    composable<PlanningRoute> {
        PlanningScreen()
    }
}

@Composable
fun PlanningScreen(modifier: Modifier = Modifier) {
    Text("Planning")
}
