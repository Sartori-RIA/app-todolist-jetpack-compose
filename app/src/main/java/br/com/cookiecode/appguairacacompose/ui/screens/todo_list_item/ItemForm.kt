package br.com.cookiecode.appguairacacompose.ui.screens.todo_list_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import br.com.cookiecode.appguairacacompose.R
import br.com.cookiecode.appguairacacompose.ui.buttons.SaveButton
import br.com.cookiecode.appguairacacompose.ui.viewmodels.ItemFormViewModel

@Composable
fun ItemFormScreen(
    todoListId: Int,
    navController: NavHostController,
    itemFormViewModel: ItemFormViewModel = viewModel()
) {
    val name by itemFormViewModel.name.observeAsState("")
    itemFormViewModel.todoListId = todoListId

    Scaffold(
        topBar = {
            TopAppBar {
                Text(
                    text = stringResource(R.string.add_item),
                    style = TextStyle(textAlign = TextAlign.Center)
                )
            }
        },
        content = {
            Surface(Modifier.fillMaxSize()) {
                ItemForm(name,
                    onSave = {
                        itemFormViewModel.save()
                        navController.popBackStack()
                    },
                    onCancel = { navController.popBackStack() },
                    onValueChange = { itemFormViewModel.onNameChanged(it) }
                )
            }
        }
    )
}

@Composable
fun ItemForm(
    name: String,
    onValueChange: (String) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .size(300.dp, 200.dp)
            .padding(16.dp)
            .verticalScroll(state = scrollState, enabled = true)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.add_item),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )

        OutlinedTextField(
            modifier = Modifier.padding(10.dp),
            value = name,
            onValueChange = onValueChange
        )

        SaveButton(onClick = onSave)
    }
}
