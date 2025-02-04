package app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import app.elephantintheroom.ijustwantsomethingtodo.screens.welcome.navigation.WelcomeRoute

fun NavGraphBuilder.welcomeScreen() {
    composable<WelcomeRoute> {
        WelcomeScreen()
    }
}

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    Text("Welcome")
}
