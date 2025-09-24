package com.sm.keepmarket.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sm.keepmarket.R

@Composable
fun AlertMessage(message: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(stringResource(R.string.label_confirmation)) },
        text = { Text(message) },
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
            }) {
                Text(stringResource(R.string.label_yes), style = MaterialTheme.typography.labelMedium)
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(stringResource(R.string.label_no), style = MaterialTheme.typography.labelMedium)
            }
        }
    )
}