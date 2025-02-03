package app.elephantintheroom.ijustwantsomethingtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.elephantintheroom.ijustwantsomethingtodo.ui.App
import app.elephantintheroom.ijustwantsomethingtodo.ui.AppUiState
import app.elephantintheroom.ijustwantsomethingtodo.ui.rememberAppUiState
import app.elephantintheroom.ijustwantsomethingtodo.ui.theme.IJustWantSomethingToDoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val appUiState = rememberAppUiState()

            ThemedScreen(appUiState)
        }
    }

    @Composable
    fun ThemedScreen(appUiState: AppUiState, modifier: Modifier = Modifier) {
        IJustWantSomethingToDoTheme {
            App(
                appUiState,
                modifier = modifier
            )
        }
    }
}
