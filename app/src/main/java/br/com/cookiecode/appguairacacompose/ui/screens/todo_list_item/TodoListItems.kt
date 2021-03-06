package br.com.cookiecode.appguairacacompose.ui.screens.todo_list_item

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.DismissDirection.EndToStart
import androidx.compose.material.DismissValue.Default
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.cookiecode.appguairacacompose.data.models.TodoListItem

@ExperimentalMaterialApi
@Composable
fun TodoListItems(
    items: List<TodoListItem>,
    updateItem: (TodoListItem) -> Unit,
    deleteItem: (TodoListItem) -> Unit,
) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
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
                directions = setOf(EndToStart),
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
                        if (dismissState.targetValue == Default) 0.75f else 1f
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
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ItemCard(
                                item = element,
                                updateItem = updateItem,
                                dismiss = dismissState.dismissDirection != null
                            )
                        }
                    }
                }
            )
        }
    }
}