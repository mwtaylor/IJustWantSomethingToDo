package app.elephantintheroom.somethingtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.somethingtodo.data.ThingToDo
import app.elephantintheroom.somethingtodo.data.thingsToDo
import app.elephantintheroom.somethingtodo.ui.theme.IJustWantSomethingToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IJustWantSomethingToDoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IJustWantSomethingToDoApp()
                }
            }
        }
    }
}

@Composable
fun IJustWantSomethingToDoApp() {
    Scaffold { contentPadding ->
        LazyColumn(contentPadding = contentPadding) {
            items(thingsToDo) {
                ThingToDoItem(it)
            }
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
        IJustWantSomethingToDoApp()
    }
}