package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import br.com.cookiecode.appguairacacompose.R
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.ui.buttons.AddFAB
import br.com.cookiecode.appguairacacompose.ui.viewmodels.TodoListsViewModel

@ExperimentalMaterialApi
@Composable
fun TodoListsScreen(
    navController: NavHostController,
    todoListViewModel: TodoListsViewModel = viewModel()
) {
    val items by todoListViewModel.items().observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name), textAlign = TextAlign.Center) }
            )
        },
        content = {
            TodoLists(
                items = items,
                onNavigate = { navController.navigate("todo_lists/${it.id}") },
                deleteItem = { todoListViewModel.delete(it) })
        },
        floatingActionButton = {
            AddFAB(
                onClick = { navController.navigate("todo_list_form") },
                id = R.string.add_todolist
            )
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun TodoLists(
    items: List<TodoList>,
    onNavigate: (TodoList) -> Unit,
    deleteItem: (TodoList) -> Unit
) {
    val lazyData = rememberLazyListState()
    LazyColumn(state = lazyData) {
        items(items) { element ->
            val dismissState = rememberDismissState(
                confirmStateChange = {
                    deleteItem(element)
                    true
                }
            )
            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier.padding(vertical = 4.dp),
                directions = setOf(DismissDirection.EndToStart),
                dismissThresholds = {
                    FractionalThreshold(0.5f)
                },
                background = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.DismissedToStart -> Color.Red
                            else -> MaterialTheme.colors.background
                        }
                    )

                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                    )

                    Box(
                        Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(horizontal = 20.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            Icons.Rounded.Delete,
                            contentDescription = "Localized description",
                            modifier = Modifier.scale(scale)
                        )
                    }
                },
                dismissContent = {
                    TodoListCard(
                        element = element,
                        onEdit = { onNavigate(element) }
                    )
                }
            )
        }
    }
}