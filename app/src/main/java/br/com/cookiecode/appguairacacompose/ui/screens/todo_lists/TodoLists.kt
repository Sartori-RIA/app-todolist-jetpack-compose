package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
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
                    Card {
                        Text(element.name)
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("wish_list_form")
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