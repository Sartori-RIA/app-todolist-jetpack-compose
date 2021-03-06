package br.com.cookiecode.appguairacacompose.ui.screens.todo_list_item

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.cookiecode.appguairacacompose.data.models.TodoListItem

@Composable
fun ItemCard(item: TodoListItem, updateItem: (TodoListItem) -> Unit, dismiss: Boolean) {
    val padding = 10.dp

    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = animateDpAsState(if (dismiss) 8.dp else 4.dp).value,
        modifier = Modifier
            .padding(padding)
            .height(50.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(item.name)
            Checkbox(
                checked = item.done,
                onCheckedChange = {
                    item.done = it
                    updateItem(item)
                },
                enabled = true
            )
        }
    }
}
