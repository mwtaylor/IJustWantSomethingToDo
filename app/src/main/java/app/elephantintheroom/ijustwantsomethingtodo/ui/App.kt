package app.elephantintheroom.ijustwantsomethingtodo.ui

import androidx.compose.foundation.layout.Box
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
import androidx.navigation.compose.rememberNavController
import app.elephantintheroom.ijustwantsomethingtodo.navigation.AppDestinations
import app.elephantintheroom.ijustwantsomethingtodo.ui.navigation.AppNavHost
import app.elephantintheroom.ijustwantsomethingtodo.ui.navigation.AppNavigationSuiteScaffold
import app.elephantintheroom.ijustwantsomethingtodo.ui.theme.IJustWantSomethingToDoTheme

@Composable
fun App(
    appUiState: AppUiState,
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
                    onClick = { it.onClick(appUiState.navController) },
                )
            }
        },
    ) {
        Scaffold(
            modifier = modifier
        ) { padding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                AppNavHost(appUiState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    IJustWantSomethingToDoTheme {
        App(
            AppUiState(rememberNavController()),
        )
    }
}
