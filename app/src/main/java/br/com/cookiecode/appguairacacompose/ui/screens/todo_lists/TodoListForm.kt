package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.com.cookiecode.appguairacacompose.R
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListRepository
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
            repository.create(TodoList(name = _name.value.toString()))
        }
    }
}

@Composable
fun TodoListFormScreen(
    navController: NavHostController,
    todoListViewModel: TodoListFormViewModel = viewModel()
) {
    val name by todoListViewModel.name.observeAsState("")

    TodoListForm(name,
        onSave = {
            todoListViewModel.save()
            navController.popBackStack()
        },
        onCancel = { navController.popBackStack() },
        onValueChange = { todoListViewModel.onNameChanged(it) }
    )
}

@Composable
fun TodoListForm(
    name: String,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    onValueChange: (String) -> Unit
) {
    Surface(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = name,
                onValueChange = onValueChange,
                label = { Text(stringResource(R.string.label_name)) }
            )
            TextButton(
                onClick = onSave,
                content = {
                    Text(stringResource(R.string.btn_save))
                }
            )
        }
    }
}