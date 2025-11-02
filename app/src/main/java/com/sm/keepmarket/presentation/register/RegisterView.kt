package com.sm.keepmarket.presentation.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.presentation.navigation.Dest
import com.sm.keepmarket.presentation.LocalNavHostController
import com.sm.keepmarket.presentation.components.CustomButton
import com.sm.keepmarket.presentation.components.InputTextField
import com.sm.keepmarket.presentation.theme.Green
import com.sm.keepmarket.presentation.theme.Red
import com.sm.keepmarket.util.UiStateView
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterView() {

    val context = LocalContext.current
    val viewModel: RegisterViewModel = koinViewModel()
    val navController = LocalNavHostController.current
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.state is UiStateView.Success){

        if((uiState.state as UiStateView.Success<Boolean>).data){
            navController.navigate(Dest.SplashView)
            return
        }

        LaunchedEffect(uiState) {
            Toast.makeText(context, "User register failure!", Toast.LENGTH_SHORT).show()
        }

    }

    Column(modifier = Modifier.fillMaxSize().padding(top = 16.dp), verticalArrangement = Arrangement.Top) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically){
                IconButton(modifier = Modifier.padding(16.dp), onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.arrowlefticon),
                        tint = Color.Unspecified,
                        contentDescription = "Arrow back view"
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "Sign Up",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 25.sp
                )
            }
        }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center) {
            InputTextField(modifier = Modifier.fillMaxWidth(), isError = uiState.email.errorMessage.isNotEmpty(), value = uiState.email.value, placeHolder = "Email") { value ->
                viewModel.onEmailChanged(value)
            }
            if(uiState.email.errorMessage.isNotEmpty()){
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = uiState.email.errorMessage, color = Red, style = MaterialTheme.typography.labelSmall, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(5.dp))

            InputTextField(modifier = Modifier.fillMaxWidth(), isError = uiState.userName.errorMessage.isNotEmpty(), value = uiState.userName.value, placeHolder = "Create user name") { value ->
                viewModel.onUserNameChanged(value)
            }

            if(uiState.userName.errorMessage.isNotEmpty()){
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = uiState.userName.errorMessage, color = Red, style = MaterialTheme.typography.labelSmall, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(5.dp))

            InputTextField(modifier = Modifier.fillMaxWidth(), isPassword = true, isError = uiState.password.errorMessage.isNotEmpty(), value = uiState.password.value, placeHolder = "Password") { value ->
                viewModel.onPasswordChanged(value)
            }

            Column {

                if(uiState.password.value.isNotEmpty()){
                    uiState.password.onChangeValidation.forEach {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = it.description, color = if(it.isValid) Green else Red, style = MaterialTheme.typography.labelSmall, fontSize = 12.sp)
                    }
                }
            }

            if(uiState.password.errorMessage.isNotEmpty()){
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = uiState.password.errorMessage, color = Red, style = MaterialTheme.typography.labelSmall, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(5.dp))

            InputTextField(modifier = Modifier.fillMaxWidth(), isPassword = true, isError = uiState.confirmPassword.errorMessage.isNotEmpty(), value = uiState.confirmPassword.value, placeHolder = "Confirm password") { value ->
                viewModel.onConfirmPasswordChanged(value)
            }

            if(uiState.confirmPassword.errorMessage.isNotEmpty()){
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = uiState.confirmPassword.errorMessage, color = Red, style = MaterialTheme.typography.labelSmall, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(modifier = Modifier.fillMaxWidth(), label = "Register") {
                viewModel.onRegisterUser()
            }
        }


    }

}
