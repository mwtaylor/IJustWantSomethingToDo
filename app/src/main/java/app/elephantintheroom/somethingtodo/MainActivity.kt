package app.elephantintheroom.somethingtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.elephantintheroom.somethingtodo.data.previewThingsToDo
import app.elephantintheroom.somethingtodo.data.previewTimeSpent
import app.elephantintheroom.somethingtodo.ui.AddThingToDoViewModel
import app.elephantintheroom.somethingtodo.ui.AppViewModel
import app.elephantintheroom.somethingtodo.ui.ThingsToDoViewModel
import app.elephantintheroom.somethingtodo.ui.AppViewModelProvider
import app.elephantintheroom.somethingtodo.ui.BaseViewModel
import app.elephantintheroom.somethingtodo.ui.ThingToDoWithTimeSpent
import app.elephantintheroom.somethingtodo.ui.components.AddNewThingToDoFloatingButton
import app.elephantintheroom.somethingtodo.ui.screens.AddThingToDoScreen
import app.elephantintheroom.somethingtodo.ui.screens.ThingsToDoScreen
import app.elephantintheroom.somethingtodo.ui.theme.IJustWantSomethingToDoTheme

enum class AppScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    AddThingToDo(title = R.string.add_thing_to_do_route)
}

class MainActivity : ComponentActivity() {
    private val viewModel: AppViewModel by viewModels { AppViewModelProvider.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IJustWantSomethingToDoTheme {
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
    viewModel: AppViewModel,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        floatingActionButton = {
            AddNewThingToDoFloatingButton(
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
            composable(route = AppScreen.Start.name) { navBackStackEntry ->
                val thingsToDoViewModel: ThingsToDoViewModel = viewModel(navBackStackEntry, factory = AppViewModelProvider.Factory)
                val uiState by thingsToDoViewModel.uiState.collectAsState()
                ThingsToDoScreen(
                    uiState.thingsToDo,
                    activeTimeSpent = uiState.activeThingToDo,
                    startSpendingTime = thingsToDoViewModel::startSpendingTime,
                    stopSpendingTime = thingsToDoViewModel::stopSpendingTime,
                )
            }
            composable(route = AppScreen.AddThingToDo.name) { navBackStackEntry ->
                val addViewModel: AddThingToDoViewModel = viewModel(navBackStackEntry, factory = AppViewModelProvider.Factory)
                AddThingToDoScreen(
                    onAddThingToDo = {
                        addViewModel.addThingToDo(it)
                        navController.navigate(AppScreen.Start.name)
                    }
                )
            }
        }
    }
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
        ThingsToDoScreen(
            thingsToDo = thingsToDoWithTimeSpent,
            activeTimeSpent = activeThingToDo?.let { it.thingToDo to it.activeTimeSpent!! },
            startSpendingTime = {},
            stopSpendingTime = { _, _ -> },
        )
    }
}
