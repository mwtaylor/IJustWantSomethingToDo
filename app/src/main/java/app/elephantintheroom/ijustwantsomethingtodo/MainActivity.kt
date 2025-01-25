package app.elephantintheroom.ijustwantsomethingtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import app.elephantintheroom.ijustwantsomethingtodo.ui.AppUiState
import app.elephantintheroom.ijustwantsomethingtodo.ui.AppViewModel
import app.elephantintheroom.ijustwantsomethingtodo.ui.MainScreen
import app.elephantintheroom.ijustwantsomethingtodo.ui.theme.IJustWantSomethingToDoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                appViewModel.uiState.collect {
                    setContent {
                        ThemedScreen(it)
                    }
                }
            }
        }
    }

    @Composable
    fun ThemedScreen(uiState: AppUiState) {
        IJustWantSomethingToDoTheme {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                MainScreen(
                    uiState,
                    appViewModel::startTask,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}
