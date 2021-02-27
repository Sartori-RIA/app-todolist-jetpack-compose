package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.com.cookiecode.appguairacacompose.R
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.data.models.TodoListWithItems
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListRepository


class TodoListDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoListRepository(application)
    private val _data = MutableLiveData(
        TodoListWithItems(
            items = listOf(),
            todo = TodoList("")
        )
    )

    var data: LiveData<TodoListWithItems> = _data

    fun onIdChanged(id: Int) {
        data = repository.show(id)
    }
}

@Composable
fun TodoListDetail(
    navController: NavHostController,
    todoListDetailViewModel: TodoListDetailViewModel = viewModel(),
    id: Int
) {
    todoListDetailViewModel.onIdChanged(id)

    val todoList by todoListDetailViewModel.data.observeAsState(
        TodoListWithItems(
            items = listOf(),
            todo = TodoList("")
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(todoList.todo.name, textAlign = TextAlign.Center)
            })
        },
        content = {
            Text(todoList.todo.name)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                },
                content = {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = stringResource(R.string.add_item),
                    )
                }
            )
        }
    )
}

@Preview
@Composable
fun ItemCard() {
    val padding = 16.dp
    var checked = false
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {}
        Spacer(Modifier.padding(padding))
        Card(
            shape = RoundedCornerShape(4.dp),
            elevation = 4.dp,
            modifier = Modifier
                .padding(padding)
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(padding),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(" item 1")
                Checkbox(checked = checked, onCheckedChange = { checked = it })
            }
        }
    }
}
