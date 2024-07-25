package app.elephantintheroom.somethingtodo.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import app.elephantintheroom.somethingtodo.R
import kotlinx.coroutines.delay
import java.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun UpdatingDuration(
    getCurrentDuration: () -> Duration
) {
    val initialDuration = getCurrentDuration()
    var timerHours by remember { mutableIntStateOf(initialDuration.toHoursPart()) }
    var timerMinutes by remember { mutableIntStateOf(initialDuration.toMinutesPart()) }
    var timerSeconds by remember { mutableIntStateOf(initialDuration.toSecondsPart()) }
    LaunchedEffect(initialDuration) {
        while(true) {
            delay(200.milliseconds)
            val duration = getCurrentDuration()
            timerHours = duration.toHoursPart()
            timerMinutes = duration.toMinutesPart()
            timerSeconds = duration.toSecondsPart()
        }
    }
    Text(
        text = stringResource(
            R.string.formatted_duration,
            timerHours,
            timerMinutes,
            timerSeconds,
        )
    )
}
