package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

class TodoListViewModel : ViewModel() {
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    fun onNameChanged(newName: String) {
        _name.value = newName
    }
}

@Composable
fun TodoListFormScreen(
    navController: NavHostController,
    todoListViewModel: TodoListViewModel = viewModel()
) {
    val name by todoListViewModel.name.observeAsState("")

    TodoListForm(name,
        onSave = {
            Log.d("CLICK", "SALVAR")
        },
        onCancel = { navController.popBackStack() },
        onValueChange = { todoListViewModel.onNameChanged(it) }
    )
}

@Preview
@Composable
fun TodoListForm(
    name: String,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    onValueChange: (String) -> Unit
) {
    Surface(Modifier.fillMaxSize()) {
        Column {
            OutlinedTextField(
                value = name,
                onValueChange = onValueChange,
                label = { Text("Nome") }
            )
            TextButton(
                onClick = onSave,
                content = {
                    Text("Salvar")
                }
            )
        }
    }
}