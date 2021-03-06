package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.com.cookiecode.appguairacacompose.R
import br.com.cookiecode.appguairacacompose.ui.buttons.SaveButton
import br.com.cookiecode.appguairacacompose.ui.viewmodels.TodoListFormViewModel

@Composable
fun TodoListFormScreen(
    navController: NavHostController,
    todoListViewModel: TodoListFormViewModel = viewModel(),
    id: Int?
) {
    val name by todoListViewModel.name.observeAsState("")


    Scaffold(
        topBar = {
            TopAppBar {
                Text(
                    text = stringResource(R.string.add_todolist),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
            }
        },
        content = {
            Surface(Modifier.fillMaxSize()) {
                TodoListForm(name,
                    onSave = {
                        todoListViewModel.save()
                        navController.popBackStack()
                    },
                    onCancel = { navController.popBackStack() },
                    onValueChange = { todoListViewModel.onNameChanged(it) }
                )
            }
        }
    )
}

@Composable
fun TodoListForm(
    name: String,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    onValueChange: (String) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .size(300.dp, 200.dp)
            .padding(16.dp)
            .verticalScroll(state = scrollState, enabled = true)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.add_todolist),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4
        )
        OutlinedTextField(
            modifier = Modifier.padding(10.dp),
            value = name,
            onValueChange = onValueChange,
            label = { Text(stringResource(R.string.label_name)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
        )
        SaveButton(onClick = onSave)
    }

}