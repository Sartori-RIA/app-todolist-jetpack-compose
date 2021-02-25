package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import br.com.cookiecode.appguairacacompose.data.models.TodoList
import java.util.ArrayList


@Composable
fun TodoLists(navController: NavHostController) {
    val lazyData = rememberLazyListState()
    val dummyData = ArrayList<TodoList>()

    for (i in 1..400)
        dummyData.add(TodoList(i, "item $i"))

    Scaffold(
        content = {
            LazyColumn(state = lazyData) {
                itemsIndexed(dummyData) { index, element ->
                    TodoListCard(element)
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
                        contentDescription = "Add WishList",
                    )
                }
            )
        }
    )
}

@Preview
@Composable
fun TodoListCard(element: TodoList) {
    val padding = 16.dp
    Column(
        Modifier
            .clickable(onClick = {

            })
            .padding(padding)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) { /*...*/ }
        Spacer(Modifier.padding(padding))
        Card(elevation = 4.dp, backgroundColor = MaterialTheme.colors.background) {
            Text("sada")
        }
    }
}
