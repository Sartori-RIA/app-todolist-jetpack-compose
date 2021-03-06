package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import br.com.cookiecode.appguairacacompose.R
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.ui.buttons.AddFAB
import br.com.cookiecode.appguairacacompose.ui.screens.todo_list_item.TodoListItems
import br.com.cookiecode.appguairacacompose.ui.viewmodels.TodoListDetailViewModel

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun TodoListDetail(
    navController: NavHostController,
    todoListDetailViewModel: TodoListDetailViewModel = viewModel(),
    id: Int
) {
    val items by todoListDetailViewModel.items(id).observeAsState(listOf())
    val todolist by todoListDetailViewModel.todoList(id).observeAsState(TodoList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(todolist.name, textAlign = TextAlign.Center) }
            )
        },
        content = {
            Column {
                TodoListHeader(todolist)
                Spacer(Modifier.padding(16.dp))
                TodoListItems(items = items) {
                    todoListDetailViewModel.updateItem(it)
                }
            }
        },
        floatingActionButton = {
            AddFAB(
                onClick = {
                    navController.navigate("todo_lists/${id}/add_item")
                },
                id = R.string.add_item
            )
        }
    )
}
