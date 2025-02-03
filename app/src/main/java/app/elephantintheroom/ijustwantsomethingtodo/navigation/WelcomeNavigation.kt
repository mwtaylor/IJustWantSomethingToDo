package app.elephantintheroom.ijustwantsomethingtodo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.serialization.Serializable

@Serializable data object WelcomeRoute

fun NavController.navigateToWelcome(navOptions: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route = WelcomeRoute) {
        navOptions()
    }
}
