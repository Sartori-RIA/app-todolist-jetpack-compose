package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.cookiecode.appguairacacompose.data.models.TodoList

@ExperimentalMaterialApi
@Composable
fun TodoListCard(element: TodoList, onEdit: () -> Unit) {
    val padding = 16.dp

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