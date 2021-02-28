package br.com.cookiecode.appguairacacompose.ui.buttons

import androidx.annotation.StringRes
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun AddFAB(onClick: () -> Unit, @StringRes id: Int) {
    FloatingActionButton(
        onClick = onClick,
        content = {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = stringResource(id),
            )
        }
    )
}