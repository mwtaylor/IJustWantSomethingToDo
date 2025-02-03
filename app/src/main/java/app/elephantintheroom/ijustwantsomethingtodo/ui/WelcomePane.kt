package app.elephantintheroom.ijustwantsomethingtodo.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.elephantintheroom.ijustwantsomethingtodo.navigation.WelcomeRoute

fun NavGraphBuilder.welcomeScreen() {
    composable<WelcomeRoute> {
        WelcomeScreen()
    }
}

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Text("Welcome")
}
