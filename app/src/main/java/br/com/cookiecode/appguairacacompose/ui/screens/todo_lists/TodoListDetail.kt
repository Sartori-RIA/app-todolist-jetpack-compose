package br.com.cookiecode.appguairacacompose.ui.screens.todo_lists

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Preview
@Composable
fun TodoListDetail(navController: NavHostController) {
    Scaffold(
        content = {
            Text("ToDoListDetail")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                },
                content = {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add Item",
                    )
                }
            )
        }
    )
}
