package app.elephantintheroom.somethingtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.TimeSpent
import app.elephantintheroom.somethingtodo.data.previewThingsToDo
import app.elephantintheroom.somethingtodo.data.previewTimeSpent
import app.elephantintheroom.somethingtodo.ui.ThingsToDoViewModel
import app.elephantintheroom.somethingtodo.ui.AppViewModelProvider
import app.elephantintheroom.somethingtodo.ui.ThingToDoWithTimeSpent
import app.elephantintheroom.somethingtodo.ui.theme.IJustWantSomethingToDoTheme
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.time.Duration.Companion.milliseconds

enum class AppScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    AddThingToDo(title = R.string.add_thing_to_do_route)
}

class MainActivity : ComponentActivity() {
    private val viewModel: ThingsToDoViewModel by viewModels { AppViewModelProvider.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IJustWantSomethingToDoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IJustWantSomethingToDoApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun IJustWantSomethingToDoApp(
    viewModel: ThingsToDoViewModel,
    navController: NavHostController = rememberNavController(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingAddButton(
                onAddNewThingToDo = { navController.navigate(AppScreen.AddThingToDo.name) }
            )
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            composable(route = AppScreen.Start.name) {
                AppContent(
                    uiState.thingsToDo,
                    activeTimeSpent = uiState.activeThingToDo,
                    startSpendingTime = viewModel::startSpendingTime,
                    stopSpendingTime = viewModel::stopSpendingTime,
                )
            }
            composable(route = AppScreen.AddThingToDo.name) {
                AddThingToDo()
            }
        }
    }
}

@Composable
fun FloatingAddButton(
    onAddNewThingToDo: () -> Unit
) {
    FloatingActionButton(onClick = { onAddNewThingToDo.invoke() }) {
        Icon(Icons.Default.Add, contentDescription = "Add")
    }
}

@Composable
private fun AppContent(
    thingsToDo: List<ThingToDoWithTimeSpent>,
    modifier: Modifier = Modifier,
    activeTimeSpent: Pair<ThingToDo, TimeSpent>? = null,
    startSpendingTime: (ThingToDo) -> Unit,
    stopSpendingTime: (ThingToDo, TimeSpent) -> Unit,
) {
    Column(modifier) {
        if (activeTimeSpent != null) {
            Row(
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding),
                    horizontal = dimensionResource(R.dimen.double_padding)
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StopSpendingTimeButton(activeTimeSpent.first) {
                    stopSpendingTime(activeTimeSpent.first, activeTimeSpent.second)
                }
                Text(text = activeTimeSpent.first.name)
                Spacer(modifier = Modifier.weight(1.0F))
                UpdatingDuration { Duration.between(activeTimeSpent.second.started, Instant.now()) }
            }
        }
        if (thingsToDo.isEmpty()) {
            Text(text = "Nothing available to do right now")
        } else {
            ThingsToDoList(
                thingsToDo,
                startSpendingTime = startSpendingTime,
                stopSpendingTime = stopSpendingTime
            )
        }
    }
}

@Composable
private fun ThingsToDoList(
    thingsToDo: List<ThingToDoWithTimeSpent>,
    modifier: Modifier = Modifier,
    startSpendingTime: (ThingToDo) -> Unit,
    stopSpendingTime: (ThingToDo, TimeSpent) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(thingsToDo.toList()) {
            ThingToDoItem(
                it.thingToDo,
                it.activeTimeSpent,
                it.recentTimeSpent.getOrDefault(ChronoUnit.DAYS to 0, Duration.ZERO),
                { startSpendingTime(it.thingToDo) },
                { timeSpent: TimeSpent -> stopSpendingTime(it.thingToDo, timeSpent) },
            )
        }
    }
}

@Composable
private fun ThingToDoItem(
    thingToDo: ThingToDo,
    activeTimeSpent: TimeSpent?,
    totalTimeSpentToday: Duration,
    startSpendingTime: () -> Unit,
    stopSpendingTime: (TimeSpent) -> Unit,
    startExpanded: Boolean = false,
) {
    var expanded by remember { mutableStateOf(startExpanded) }
    val color by animateColorAsState(
        targetValue = if (activeTimeSpent != null) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            MaterialTheme.colorScheme.secondaryContainer
        },
        label = "Animate Thing To Do Card Color"
    )
    Card(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding))
            .background(color),
        colors = CardDefaults.cardColors(
            containerColor = color,
            contentColor = if (activeTimeSpent != null) {
                MaterialTheme.colorScheme.onPrimaryContainer
            } else {
               MaterialTheme.colorScheme.onSecondaryContainer
           },
        )
    ) {
        Column(
            modifier = Modifier.animateContentSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (activeTimeSpent == null) {
                    StartSpendingTimeButton(thingToDo, startSpendingTime)
                } else {
                    StopSpendingTimeButton(thingToDo) { stopSpendingTime(activeTimeSpent) }
                }
                Text(text = thingToDo.name)
                Spacer(Modifier.weight(1.0F))
                IconButton(onClick = { expanded = !expanded }) {
                    if (expanded) {
                        Icon(
                            imageVector = Icons.Filled.ExpandLess,
                            contentDescription =
                                stringResource(R.string.collapse_thing_to_do, thingToDo.name)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.ExpandMore,
                            contentDescription =
                                stringResource(R.string.expand_thing_to_do, thingToDo.name)
                        )
                    }
                }
            }
            if (expanded) {
                // How to recompose when going to next day but otherwise view model doesn't change?
                val startOfDay = Instant.now().truncatedTo(ChronoUnit.DAYS)
                val totalTimeSpentTodayIncludingActive = {
                    totalTimeSpentToday +
                            if (activeTimeSpent != null) {
                                val started = if (activeTimeSpent.started < startOfDay) {
                                    startOfDay
                                } else {
                                    activeTimeSpent.started
                                }
                                Duration.between(activeTimeSpent.started, Instant.now())
                            } else {
                                Duration.ZERO
                            }
                }
                Row(
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding))
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    UpdatingDuration(totalTimeSpentTodayIncludingActive)
                }
            }
        }
    }
}

@Composable
fun StartSpendingTimeButton(
    thingToDo: ThingToDo,
    startSpendingTime: () -> Unit,
) {
    IconButton(onClick = startSpendingTime) {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = stringResource(R.string.start_spending_time_icon_description, thingToDo.name)
        )
    }
}

@Composable
fun StopSpendingTimeButton(
    thingToDo: ThingToDo,
    stopSpendingTime: () -> Unit,
) {
    IconButton(onClick = { stopSpendingTime() }) {
        Icon(
            imageVector = Icons.Filled.Stop,
            contentDescription = stringResource(R.string.stop_spending_time_icon_description, thingToDo.name)
        )
    }
}

@Composable
private fun AddThingToDo(
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(text = "Add something new to do")
    }
}

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

@Preview(showBackground = true)
@Composable
fun IJustWantSomethingToDoAppPreview() {
    val thingsToDoWithTimeSpent = previewThingsToDo.map { thingToDo ->
        ThingToDoWithTimeSpent(
            thingToDo,
            previewTimeSpent.singleOrNull { it.thingToDoId == thingToDo.id && it.finished == null }
        )
    }
    val activeThingToDo = thingsToDoWithTimeSpent.firstOrNull { it.activeTimeSpent != null }
    IJustWantSomethingToDoTheme {
        AppContent(
            thingsToDo = thingsToDoWithTimeSpent,
            activeTimeSpent = activeThingToDo?.let { it.thingToDo to it.activeTimeSpent!! },
            startSpendingTime = {},
            stopSpendingTime = { _, _ -> },
        )
    }
}

@Preview
@Composable
fun ExpandedThingToDoPreview() {
    val thingToDo = previewThingsToDo.single { it.name == "Work" }
    val activeTimeSpent = previewTimeSpent.single { it.thingToDoId == thingToDo.id && it.finished == null }
    IJustWantSomethingToDoTheme {
        ThingToDoItem(
            thingToDo = thingToDo,
            activeTimeSpent = activeTimeSpent,
            totalTimeSpentToday = Duration.ofMinutes(3),
            startSpendingTime = {},
            stopSpendingTime = {},
            startExpanded = true,
        )
    }
}
