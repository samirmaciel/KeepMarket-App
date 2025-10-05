package com.sm.keepmarket.presentation.register

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sm.keepmarket.R
import com.sm.keepmarket.domain.model.ValidationModel
import com.sm.keepmarket.presentation.components.CustomButton
import com.sm.keepmarket.presentation.components.InputTextField
import com.sm.keepmarket.presentation.theme.Green
import com.sm.keepmarket.presentation.theme.KeepMarketTheme
import com.sm.keepmarket.presentation.theme.Red
import com.sm.keepmarket.util.UiStateView
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterView() {

    val viewModel: RegisterViewModel = koinViewModel()
    //val navController = LocalNavHostController.current
    var emailValue by remember { mutableStateOf("") }
    var userNameValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var confirmPasswordValue by remember { mutableStateOf("") }

    var showEmailError by remember { mutableStateOf(false) }
    var showUserNameError by remember { mutableStateOf(false) }
    var showPasswordError by remember { mutableStateOf(false) }
    var showConfirmPasswordError by remember { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsState()
    val passwordValidationState by viewModel.passwordValidationItemListState.collectAsState()


    Column(modifier = Modifier.fillMaxSize().padding(top = 16.dp), verticalArrangement = Arrangement.Top) {

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Row (modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically){
                IconButton(modifier = Modifier.padding(16.dp), onClick = {
                    //navController.navigateUp()
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
            InputTextField(modifier = Modifier.fillMaxWidth(), isError = showEmailError, value = emailValue, placeHolder = "Email") { value ->

                if(value.isNotEmpty()){
                    showEmailError = false
                }

                emailValue = value
            }

            Spacer(modifier = Modifier.height(5.dp))

            InputTextField(modifier = Modifier.fillMaxWidth(), isError = showUserNameError, value = userNameValue, placeHolder = "Create user name") { value ->
                if(value.isNotEmpty()){
                    showUserNameError = false
                }

                userNameValue = value
            }

            Spacer(modifier = Modifier.height(5.dp))

            InputTextField(modifier = Modifier.fillMaxWidth(), isPassword = true, isError = showPasswordError, value = passwordValue, placeHolder = "Password") { value ->
                if(value.isNotEmpty()){
                    showPasswordError = false
                    viewModel.validatePassword(value)
                }else{
                    viewModel.clearPasswordValidation()
                }

                passwordValue = value
            }

            Column {
                if(passwordValidationState is UiStateView.Success){
                    (passwordValidationState as UiStateView.Success<List<ValidationModel>>).data.forEach {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = it.description, color = if(it.isValid) Green else Red, style = MaterialTheme.typography.labelSmall, fontSize = 12.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            InputTextField(modifier = Modifier.fillMaxWidth(), isPassword = true, isError = showConfirmPasswordError, value = confirmPasswordValue, placeHolder = "Confirm password") { value ->
                if(value.isNotEmpty()){
                    showConfirmPasswordError = false
                }

                confirmPasswordValue = value
            }

            Spacer(modifier = Modifier.height(10.dp))

            CustomButton(modifier = Modifier.fillMaxWidth(), label = "Register") {
                showPasswordError = !viewModel.allPasswordValidationIsValid()

                if(showPasswordError || showEmailError || showUserNameError || showConfirmPasswordError) return@CustomButton
            }
        }


    }

}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {

    KeepMarketTheme {
        RegisterView()
    }
}