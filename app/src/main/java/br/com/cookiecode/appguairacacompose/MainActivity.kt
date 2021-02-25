package br.com.cookiecode.appguairacacompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import br.com.cookiecode.appguairacacompose.ui.screens.todo_lists.TodoListDetail
import br.com.cookiecode.appguairacacompose.ui.screens.todo_lists.TodoListForm
import br.com.cookiecode.appguairacacompose.ui.screens.todo_lists.TodoLists
import br.com.cookiecode.appguairacacompose.ui.theme.AppGuairacaComposeTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppGuairacaComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = "todo_lists") {
                        composable("todo_lists") { TodoLists(navController) }
                        composable("todo_list_form") { TodoListForm(navController) }
                        composable(
                            "todo_list/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { TodoListDetail(navController) }
                    }
                }
            }
        }
    }
}