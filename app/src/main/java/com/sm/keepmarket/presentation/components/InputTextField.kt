package com.sm.keepmarket.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeHolder: String,
    isError: Boolean = false,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {

    OutlinedTextField(
        isError = isError,
        value = value,
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.primary
        ),
        onValueChange = onValueChange,
        label = {

            Text(
                text = placeHolder,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

        },
        modifier = modifier,
    )
}