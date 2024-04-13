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
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.CreationExtras
import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.thingsToDo
import app.elephantintheroom.somethingtodo.ui.ThingsToDoViewModel
import app.elephantintheroom.somethingtodo.ui.ThingsToDoViewModelProvider
import app.elephantintheroom.somethingtodo.ui.theme.IJustWantSomethingToDoTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ThingsToDoViewModel by viewModels { ThingsToDoViewModelProvider.Factory }

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
        ThingsToDoList(uiState.thingsToDo, Modifier.padding(contentPadding))
    }
}

@Composable
private fun ThingsToDoList(thingsToDo: List<ThingToDo> = listOf(), modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(thingsToDo) {
            ThingToDoItem(it)
        }
    }
}

@Composable
private fun ThingToDoItem(thingToDo: ThingToDo) {
    Card(modifier = Modifier.padding(dimensionResource(R.dimen.padding))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding))
        ) {
            Text(text = thingToDo.name)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IJustWantSomethingToDoAppPreview() {
    IJustWantSomethingToDoTheme {
        ThingsToDoList(
            thingsToDo
        )
    }
}
