package app.elephantintheroom.ijustwantsomethingtodo.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.elephantintheroom.ijustwantsomethingtodo.app.navigation.AppDestinations
import app.elephantintheroom.ijustwantsomethingtodo.app.ui.navigation.AppNavHost
import app.elephantintheroom.ijustwantsomethingtodo.app.ui.navigation.AppNavigationSuiteScaffold
import app.elephantintheroom.ijustwantsomethingtodo.app.ui.theme.IJustWantSomethingToDoTheme

@Composable
fun App(
    uiState: AppUiState,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    AppNavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            Icons.Rounded.Person,
                            contentDescription = ""
                        )
                    },
                    label = { Text(stringResource(it.label)) },
                    selected = false,
                    onClick = { it.onClick(navController) },
                )
            }
        },
    ) {
        Scaffold(
            modifier = modifier
        ) { innerPadding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding)
            ) {
                AppNavHost(navController, uiState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    IJustWantSomethingToDoTheme {
        App(
            AppUiState(
                false,
                null,
            ),
            rememberNavController(),
        )
    }
}
