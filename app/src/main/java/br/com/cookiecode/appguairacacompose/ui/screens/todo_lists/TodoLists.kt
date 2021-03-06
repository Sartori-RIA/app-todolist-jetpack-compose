package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import br.com.cookiecode.appguairacacompose.R
import br.com.cookiecode.appguairacacompose.ui.buttons.AddFAB
import br.com.cookiecode.appguairacacompose.ui.viewmodels.TodoListsViewModel

@ExperimentalMaterialApi
@Composable
fun TodoLists(
    navController: NavHostController,
    todoListViewModel: TodoListsViewModel = viewModel()
) {
    val lazyData = rememberLazyListState()
    val items by todoListViewModel.items().observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name), textAlign = TextAlign.Center) }
            )
        },
        content = {
            LazyColumn(state = lazyData) {
                items(items) { element ->
                    TodoListCard(element, onEdit = {
                        navController.navigate("todo_lists/${element.id}")
                    })
                }
            }
        },
        floatingActionButton = {
            AddFAB(
                onClick = { navController.navigate("todo_list_form") },
                id = R.string.add_todolist
            )
        }
    )
}
