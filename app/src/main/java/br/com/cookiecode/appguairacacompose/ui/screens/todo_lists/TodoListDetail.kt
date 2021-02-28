package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import android.app.Application
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.com.cookiecode.appguairacacompose.R
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import br.com.cookiecode.appguairacacompose.data.models.TodoListItem
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListItemRepository
import br.com.cookiecode.appguairacacompose.data.repositories.TodoListRepository
import br.com.cookiecode.appguairacacompose.ui.buttons.AddFAB
import br.com.cookiecode.appguairacacompose.ui.screens.todo_list_item.ItemCard
import br.com.cookiecode.appguairacacompose.ui.screens.todo_list_item.ItemForm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TodoListDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TodoListRepository(application)
    private val itemRepository = TodoListItemRepository(application)

    fun items(id: Int) = itemRepository.getItemsByTodoList(id)

    fun todoList(id: Int) = repository.show(id)

    fun updateItem(item: TodoListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            itemRepository.update(item)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun TodoListDetail(
    navController: NavHostController,
    todoListDetailViewModel: TodoListDetailViewModel = viewModel(),
    id: Int
) {
    val items by todoListDetailViewModel.items(id).observeAsState(listOf())
    val todolist by todoListDetailViewModel.todoList(id).observeAsState(TodoList())
    var showPopup by remember { mutableStateOf(false) }

    val lazyData = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(todolist.name, textAlign = TextAlign.Center) }
            )
        },
        content = {
            Column {
                TodolistHeader(todolist)
                Spacer(Modifier.padding(16.dp))
                LazyColumn(state = lazyData) {
                    items(items) { element ->
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {}
                            ItemCard(item = element, updateItem = {
                                todoListDetailViewModel.updateItem(it)
                            })
                        }
                    }
                }
            }

            if (showPopup) {
                ItemForm(
                    dismissDialog = {
                        showPopup = false
                    },
                    todoListId = id
                )
            }
        },
        floatingActionButton = {
            AddFAB(
                onClick = { showPopup = true },
                id = R.string.add_item
            )
        }
    )
}

@Composable
fun TodolistHeader(todoList: TodoList) {
    val progress by remember { mutableStateOf(1f) }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Text(todoList.name)
            LinearProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .padding(vertical = 5.dp)
            )
        }

    }
}

