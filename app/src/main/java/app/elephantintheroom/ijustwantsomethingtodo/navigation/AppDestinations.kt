package app.elephantintheroom.ijustwantsomethingtodo.navigation

import androidx.annotation.StringRes
import app.elephantintheroom.ijustwantsomethingtodo.R

enum class AppDestinations(
    @StringRes val label: Int,
) {
    WELCOME(label = R.string.welcome),
    TASKS(label = R.string.tasks),
    PLANNING(label = R.string.planning),
    REPORTS(label = R.string.reports)
}
