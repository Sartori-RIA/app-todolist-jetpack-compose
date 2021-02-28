package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import android.app.Application
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
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.com.cookiecode.appguairacacompose.R
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListRepository
import br.com.cookiecode.appguairacacompose.ui.buttons.SaveButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoListRepository(application)
    private val _name = MutableLiveData("")

    val name: LiveData<String> = _name

    fun onNameChanged(newName: String) {
        _name.value = newName
    }

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            val a = repository.create(TodoList(name = _name.value.toString()))
            var b = 2
        }
    }
}

@Composable
fun TodoListFormScreen(
    navController: NavHostController,
    todoListViewModel: TodoListFormViewModel = viewModel()
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