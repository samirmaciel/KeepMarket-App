package com.sm.keepmarket.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.presentation.Dest
import com.sm.keepmarket.presentation.LocalNavHostController
import com.sm.keepmarket.presentation.components.CustomButton
import com.sm.keepmarket.presentation.components.InputTextField
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
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val keyboardController = LocalSoftwareKeyboardController.current

        val loginUiState by viewModel.loginUiState.collectAsState()

        LaunchedEffect(Unit) {
            delay(200)
            focusManager.clearFocus()
            keyboardController?.hide()
        }

        LaunchedEffect(context) {
            viewModel.getCurrentLogin()
        }

        if (loginUiState.state is UiStateView.Success && (loginUiState.state as UiStateView.Success<Boolean>).data) {
            navController.navigate(Dest.SplashView)
            return@Column
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
            InputTextField(
                modifier = Modifier.fillMaxWidth(),
                isError = loginUiState.email.errorMessage.isNotEmpty(),
                value = loginUiState.email.value,
                placeHolder = "Login"
            ) {
                viewModel.onUsernameChanged(it)
            }

            if (loginUiState.email.errorMessage.isNotEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    text = loginUiState.email.errorMessage,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            InputTextField(
                modifier = Modifier.fillMaxWidth(),
                value = loginUiState.password.value,
                placeHolder = "Password",
                isPassword = true,
                isError = loginUiState.password.errorMessage.isNotEmpty()
            ) {
                viewModel.onPasswordUsernameChanged(it)
            }

            if (loginUiState.password.errorMessage.isEmpty()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    text = loginUiState.password.errorMessage,
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

            CustomButton(
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(),
                label = "Enter"
            ) {
                viewModel.login()
            }

            if (loginUiState.state is UiStateView.Error) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    text = (loginUiState.state as UiStateView.Error).message,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.tertiary
                )
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
                    navController.navigate(Dest.RegisterView)
                }),
                text = styledText.toAnnotatedString(),
                style = MaterialTheme.typography.labelMedium,
                fontSize = 12.sp
            )
        }
    }
}