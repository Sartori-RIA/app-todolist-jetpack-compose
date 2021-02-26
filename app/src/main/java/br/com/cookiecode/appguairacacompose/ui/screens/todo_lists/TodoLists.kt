package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListRepository

class TodoListsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoListRepository(application)

    fun items() = repository.todoLists
}

@Composable
fun TodoLists(
    navController: NavHostController,
    todoListViewModel: TodoListsViewModel = viewModel()
) {
    val lazyData = rememberLazyListState()
    val items by todoListViewModel.items().observeAsState(listOf())

    Scaffold(
        content = {
            LazyColumn(state = lazyData) {
                itemsIndexed(items) { _, element ->
                    TodoListCard(element)
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("todo_list_form")
                },
                content = {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add WishList",
                    )
                }
            )
        }
    )
}

@Composable
fun TodoListCard(element: TodoList) {
    val padding = 16.dp
    Column(
        Modifier
            .clickable(onClick = {

            })
            .padding(padding)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) { /*...*/ }
        Spacer(Modifier.padding(padding))
        Card(elevation = 4.dp, backgroundColor = MaterialTheme.colors.background) {
            Text("sada")
        }
    }
}
