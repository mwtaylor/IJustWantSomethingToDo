package app.elephantintheroom.ijustwantsomethingtodo.ui.common

import androidx.compose.runtime.compositionLocalOf
import java.time.Clock

val LocalClock = compositionLocalOf { Clock.systemDefaultZone() }
