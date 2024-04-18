package app.elephantintheroom.somethingtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.TimeSpent
import app.elephantintheroom.somethingtodo.data.thingsToDo
import app.elephantintheroom.somethingtodo.ui.ThingsToDoViewModel
import app.elephantintheroom.somethingtodo.ui.AppViewModelProvider
import app.elephantintheroom.somethingtodo.ui.theme.IJustWantSomethingToDoTheme

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
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { contentPadding ->
        ThingsToDoList(
            uiState.thingsToDo,
            Modifier.padding(contentPadding),
            startSpendingTime = viewModel::startSpendingTime,
            stopSpendingTime = viewModel::stopSpendingTime
        )
    }
}

@Composable
private fun ThingsToDoList(
    thingsToDo: Map<ThingToDo, TimeSpent?>,
    modifier: Modifier = Modifier,
    startSpendingTime: (ThingToDo) -> Unit,
    stopSpendingTime: (ThingToDo, TimeSpent) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(thingsToDo.toList()) {
            ThingToDoItem(
                it.first,
                it.second,
                { startSpendingTime(it.first) },
                { timeSpent: TimeSpent -> stopSpendingTime(it.first, timeSpent) },
            )
        }
    }
}

@Composable
private fun ThingToDoItem(
    thingToDo: ThingToDo,
    activeTimeSpent: TimeSpent?,
    startSpendingTime: () -> Unit,
    stopSpendingTime: (TimeSpent) -> Unit,
) {
    Card(modifier = Modifier.padding(dimensionResource(R.dimen.padding))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding))
        ) {
            Text(text = thingToDo.name)
            if (activeTimeSpent == null) {
                IconButton(onClick = startSpendingTime) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = stringResource(R.string.start_spending_time_icon_description, thingToDo.name)
                    )
                }
            } else {
                IconButton(onClick = { stopSpendingTime(activeTimeSpent) }) {
                    Icon(
                        imageVector = Icons.Filled.Stop,
                        contentDescription = stringResource(R.string.stop_spending_time_icon_description, thingToDo.name)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IJustWantSomethingToDoAppPreview() {
    IJustWantSomethingToDoTheme {
        ThingsToDoList(
            thingsToDo,
            startSpendingTime = {},
            stopSpendingTime = { _, _ -> },
        )
    }
}
