package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class TodoListViewModel : ViewModel() {
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    fun onNameChanged(newName: String) {
        _name.value = newName
    }
}

@Preview
@Composable
fun TodoListForm(navController: NavHostController) {
    var name: String = ""
    Surface(Modifier.fillMaxSize()) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") }
        )
        TextButton(
            onClick = {
                Log.d("CLICK", "SALVAR")
            },
            content = {
                Text("Salvar")
            }
        )
    }
}