package br.com.cookiecode.appguairacacompose.ui.buttons

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.cookiecode.appguairacacompose.R

@Composable
fun SaveButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        content = {
            Text(stringResource(R.string.btn_save))
        }
    )
}