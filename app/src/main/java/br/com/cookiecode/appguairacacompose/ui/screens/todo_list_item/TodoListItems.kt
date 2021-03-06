package br.com.cookiecode.appguairacacompose.ui.screens.todo_list_item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import br.com.cookiecode.appguairacacompose.data.models.TodoListItem

@Composable
fun TodoListItems(items: List<TodoListItem>, updateItem: (TodoListItem) -> Unit) {
    val listState = rememberLazyListState()
    items.forEach {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ItemCard(
                    item = it,
                    updateItem = updateItem
                )
            }
        }
    }
}