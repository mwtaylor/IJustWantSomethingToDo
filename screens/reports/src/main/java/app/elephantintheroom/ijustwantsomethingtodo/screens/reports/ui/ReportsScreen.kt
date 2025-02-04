package app.elephantintheroom.ijustwantsomethingtodo.screens.reports.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.elephantintheroom.ijustwantsomethingtodo.screens.reports.navigation.ReportsRoute

fun NavGraphBuilder.reportsScreen() {
    composable<ReportsRoute> {
        ReportsScreen()
    }
}

@Composable
fun ReportsScreen(modifier: Modifier = Modifier) {
    Text("Reports")
}
