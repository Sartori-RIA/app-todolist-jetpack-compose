package br.com.cookiecode.appguairacacompose.ui.screens.todo_list_item

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.cookiecode.appguairacacompose.data.models.TodoListItem


@Composable
fun ItemCard(item: TodoListItem, updateItem: (TodoListItem) -> Unit) {
    val padding = 10.dp
    Card(
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp,
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
