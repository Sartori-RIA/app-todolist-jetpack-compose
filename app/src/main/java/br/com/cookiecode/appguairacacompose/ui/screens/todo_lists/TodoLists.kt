package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import br.com.cookiecode.appguairacacompose.R
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListRepository

class TodoListsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoListRepository(application)

    fun items() = repository.all()
}

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
                itemsIndexed(items) { _, element ->
                    TodoListCard(element, onEdit = {
                        navController.navigate("todo_list/${element.id}")
                    })
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
                        contentDescription = stringResource(R.string.add_todolist),
                    )
                }
            )
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun TodoListCard(element: TodoList, onEdit: () -> Unit) {
    val padding = 16.dp
    val swipeState = rememberSwipeableState(initialValue = false)

    /*
    .swipeable(
                    state = swipeState,
                    orientation = Orientation.Horizontal,
                )
     */
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) { /*...*/ }
        Spacer(Modifier.padding(padding))
        Card(
            shape = RoundedCornerShape(4.dp),
            elevation = 4.dp,
            modifier = Modifier
                .clickable(onClick = onEdit)
                .padding(padding)
                .height(100.dp)
                .fillMaxWidth()

        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(element.name)
            }
        }
    }
}
