package app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.isAltPressed
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.isMetaPressed
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import app.elephantintheroom.ijustwantsomethingtodo.data.model.ThingToDo
import app.elephantintheroom.ijustwantsomethingtodo.screens.thingstodo.R

@Composable
fun NewThingToDoPane(
    save: (ThingToDo) -> Unit,
    cancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var name by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(true) }
    val saveUsingInput: () -> Unit = {
        if (name.isNotBlank()) {
            isValid = true
            save(ThingToDo(null, name))
        } else {
            isValid = false
        }
    }

    Scaffold(
        modifier = modifier
            .onKeyEvent {
                if (
                    it.type == KeyEventType.KeyUp &&
                    it.key == Key.Enter &&
                    !it.isAltPressed &&
                    !it.isCtrlPressed &&
                    !it.isMetaPressed &&
                    !it.isShiftPressed
                ) {
                    saveUsingInput()
                    true
                } else {
                    false
                }
            },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedIconButton(
                    onClick = cancel
                ) {
                    Icon(
                        Icons.Default.Close,
                        stringResource(R.string.cancelAddingNewThingToDo)
                    )
                }

                Text(
                    text = stringResource(R.string.newThingToDo),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            val focusRequester = remember { FocusRequester() }
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                singleLine = true,
                isError = !isValid,
                supportingText = {
                    if (!isValid) {
                        Text(text = stringResource(R.string.nameShouldNotBeBlank))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
            )
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }

            FilledIconButton(
                onClick = saveUsingInput,
            ) {
                Icon(
                    Icons.Default.Done,
                    stringResource(R.string.saveNewThingToDo)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun NewThingToDoPanePreview() {
    NewThingToDoPane(
        {},
        {},
    )
}
