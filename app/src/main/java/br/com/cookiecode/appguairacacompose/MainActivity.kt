package br.com.cookiecode.appguairacacompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import br.com.cookiecode.appguairacacompose.ui.screens.todo_list_item.ItemFormScreen
import br.com.cookiecode.appguairacacompose.ui.screens.todo_lists.TodoListDetail
import br.com.cookiecode.appguairacacompose.ui.screens.todo_lists.TodoListFormScreen
import br.com.cookiecode.appguairacacompose.ui.screens.todo_lists.TodoListsScreen
import br.com.cookiecode.appguairacacompose.ui.theme.AppGuairacaComposeTheme

@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {

    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppGuairacaComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = "todo_lists") {
                        composable("todo_lists") { TodoListsScreen(navController) }
                        composable("todo_lists/add") {
                            TodoListFormScreen(
                                navController,
                                id = null
                            )
                        }
                        composable(
                            "todo_lists/{id}/edit",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) {
                            TodoListFormScreen(
                                navController = navController,
                                id = it.arguments!!.getInt("id", 0)
                            )
                        }
                        composable(
                            "todo_lists/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) {
                            TodoListDetail(
                                navController = navController,
                                id = it.arguments!!.getInt("id", 0)
                            )
                        }
                        composable(
                            "todo_lists/{id}/add_item",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) {
                            ItemFormScreen(
                                todoListId = it.arguments!!.getInt("id", 0),
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}