package app.elephantintheroom.ijustwantsomethingtodo.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.Instant
import kotlin.time.Duration.Companion.milliseconds
import app.elephantintheroom.ijustwantsomethingtodo.core.time.atStartOfDay

@Composable
fun UpdatingDuration(
    getCurrentDuration: () -> Duration
) {
    val initialDuration = getCurrentDuration()
    var timerHours by remember { mutableLongStateOf(initialDuration.toHours()) }
    var timerMinutes by remember { mutableIntStateOf(initialDuration.toMinutesPart()) }
    var timerSeconds by remember { mutableIntStateOf(initialDuration.toSecondsPart()) }
    LaunchedEffect(initialDuration) {
        while (true) {
            delay(200.milliseconds)
            val duration = getCurrentDuration()
            timerHours = duration.toHours()
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

@Preview
@Composable
fun UpdatingDurationPreview() {
    UpdatingDuration { Duration.between(Instant.now().atStartOfDay(), Instant.now()) }
}
