package com.sm.keepmarket.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.presentation.Dest
import com.sm.keepmarket.presentation.LocalNavHostController
import com.sm.keepmarket.presentation.theme.KeepMarketTheme
import com.sm.keepmarket.util.UiStateView
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 26.dp, end = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val navController = LocalNavHostController.current
        val viewModel: LoginViewModel = koinViewModel()
        val mainUiSate = viewModel.mainUiState.collectAsState()
        val context = LocalContext.current
        var loginInput by remember { mutableStateOf("") }
        var passwordInput by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current
        var showLoginErrorMessage by remember { mutableStateOf(false) }
        var showPasswordErrorMessage by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            delay(200)
            focusManager.clearFocus()
            keyboardController?.hide()
        }

        LaunchedEffect(context) {
            viewModel.getCurrentLogin()
        }

        when (mainUiSate.value) {
            is UiStateView.Error -> {}
            UiStateView.Loading -> {
                CircularProgressIndicator()
                return@Column
            }
            is UiStateView.Success<*> -> {
                navController.navigate(Dest.SplashView)
                return@Column
            }
        }


        Image(
            modifier = Modifier.padding(16.dp),
            painter = painterResource(R.drawable.keepmarketlogo),
            contentDescription = "App logo"
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Sign in",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            OutlinedTextField(
                isError = showLoginErrorMessage,
                value = loginInput,
                singleLine = true,
                onValueChange = {

                    if (it.isNotEmpty()) {
                        showLoginErrorMessage = false
                    }

                    loginInput = it
                },
                label = { Text("Login") },
                modifier = Modifier.fillMaxWidth(),
            )

            if (showLoginErrorMessage) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    text = stringResource(R.string.message_field_empty_error),
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                isError = showPasswordErrorMessage,
                value = passwordInput,
                singleLine = true,
                visualTransformation = if (false) VisualTransformation.None else PasswordVisualTransformation(),
                onValueChange = {

                    if (it.isNotEmpty()) {
                        showPasswordErrorMessage = false
                    }

                    passwordInput = it
                },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
            )

            if (showPasswordErrorMessage) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    text = stringResource(R.string.message_field_empty_error),
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    modifier = Modifier.clickable(true, onClick = {}),
                    text = "Forgor password?",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp
                )
            }


            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {

                    if (loginInput.isEmpty()) {
                        showLoginErrorMessage = true
                    }

                    if (passwordInput.isEmpty()) {
                        showPasswordErrorMessage = true
                    }

                    if (showLoginErrorMessage || showPasswordErrorMessage) return@Button

                    viewModel.validateInput(loginInput, passwordInput)
                },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Enter")
            }

            Spacer(modifier = Modifier.height(40.dp))

//            Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, text = "Or continue with")
//
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Box(modifier = Modifier.clip(shape = RoundedCornerShape(5.dp)).background(Color.White)){
//                IconButton(onClick = {
//
//                }) {
//                    Icon(painter = painterResource(R.drawable.googlelogo), tint = Color.Unspecified, contentDescription = "google logo")
//                }
//            }
        }

        Column(modifier = Modifier.padding(bottom = 16.dp)) {

            val styledText = AnnotatedString.Builder()

            styledText.apply {

                append("If you don’t have an account register\n")
                append("You can ")
                pushStyle(SpanStyle(color = Color.Blue, fontWeight = FontWeight.Black))
                append("Register here!")
            }


            Text(
                modifier = Modifier.clickable(true, onClick = {

                }),
                text = styledText.toAnnotatedString(),
                style = MaterialTheme.typography.labelMedium,
                fontSize = 12.sp
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {

    KeepMarketTheme {
        LoginView()
    }
}