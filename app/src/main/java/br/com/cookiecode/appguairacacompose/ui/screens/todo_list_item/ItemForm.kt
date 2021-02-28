package br.com.cookiecode.appguairacacompose.ui.screens.todo_list_item

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.cookiecode.appguairacacompose.R
import br.com.cookiecode.appguairacacompose.data.models.TodoListItem
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListItemRepository
import br.com.cookiecode.appguairacacompose.ui.buttons.SaveButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoListItemRepository(application)
    private val _name = MutableLiveData("")

    val name: LiveData<String> = _name
    var todoListId: Int = 0

    fun onNameChanged(newName: String) {
        _name.value = newName
    }

    fun save(done: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.create(
                TodoListItem(
                    name = _name.value.toString(),
                    toDoId = todoListId,
                    done = false
                )
            )
            done()
        }
    }
}

@Composable
fun ItemForm(
    dismissDialog: () -> Unit,
    todoListId: Int,
    itemFormViewModel: ItemFormViewModel = viewModel()
) {

    itemFormViewModel.todoListId = todoListId

    val name by itemFormViewModel.name.observeAsState("")

    AlertDialog(
        onDismissRequest = dismissDialog,
        title = {
            Text(
                text = stringResource(R.string.add_item),
                style = TextStyle(textAlign = TextAlign.Center)
            )
        },
        text = {
            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                value = name,
                onValueChange = { itemFormViewModel.onNameChanged(it) }
            )
        },
        confirmButton = {
            SaveButton(onClick = {
                itemFormViewModel.save {
                    dismissDialog()
                }
            })
        }
    )
}