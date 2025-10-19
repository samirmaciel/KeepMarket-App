package com.sm.keepmarket.presentation.register

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.ViewModel
import com.sm.keepmarket.domain.model.ValidationInput
import com.sm.keepmarket.domain.model.ValidationModel
import com.sm.keepmarket.presentation.login.InputState
import com.sm.keepmarket.util.UiStateView
import com.sm.keepmarket.util.ValidationType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _RegisterUiState: MutableStateFlow<RegisterUiState> =
        MutableStateFlow(RegisterUiState())
    val uiState = _RegisterUiState.asStateFlow()

    private fun getPasswordValidationItemList(): List<ValidationModel> {
        val validationItemList = listOf(
            ValidationModel(
                validationType = ValidationType.MORE_OR_EQUAL_THAN_8_CHARACTERS,
                description = "Maior ou igual a 8 caracteres"
            ),
            ValidationModel(
                validationType = ValidationType.ONE_CAPITAL_LETTER,
                description = "Pelo menos uma letra maiúscula"
            ),
        )

        return validationItemList
    }

    private fun getEmailValidationItemList(): List<ValidationModel> {
        val validationItemList = listOf(
            ValidationModel(
                validationType = ValidationType.IS_NOT_EMPTY,
                "O Campo não pode estar vazio"
            )
        )

        return validationItemList
    }

    private fun getUserNameValidationItemList(): List<ValidationModel> {
        val validationItemList = listOf(
            ValidationModel(
                validationType = ValidationType.IS_NOT_EMPTY,
                "O Campo não pode estar vazio"
            )
        )

        return validationItemList
    }

    private fun getConfirmPasswordValidationItemList(): List<ValidationModel> {
        val validationItemList = listOf(
            ValidationModel(
                validationType = ValidationType.IS_NOT_EMPTY,
                "O Campo não pode estar vazio"
            )
        )

        return validationItemList
    }

    fun onRegisterUser() {

        var hasError = false
        var currentState = _RegisterUiState.value
        val email = currentState.email.value
        val userName = currentState.userName.value
        val password = currentState.password
        val confirmPassword = currentState.confirmPassword.value

        if(email.isEmpty()){
            hasError = true
            currentState = currentState.copy(
                email = currentState.email.copy(
                    errorMessage = "* Campo obrigatório"
                )
            )
        }

        if(userName.isEmpty()){
            hasError = true
            currentState = currentState.copy(
                userName = currentState.userName.copy(
                    errorMessage = "* Campo obrigatório"
                )
            )
        }

        if(password.value.isEmpty()){
            hasError = true
            currentState = currentState.copy(
                password = currentState.password.copy(
                    errorMessage = "* Campo obrigatório"
                )
            )
        }

        if(confirmPassword.isEmpty()){
            hasError = true
            currentState = currentState.copy(
                confirmPassword = currentState.confirmPassword.copy(
                    errorMessage = "* Campo obrigatório"
                )
            )
        }

        if(confirmPassword.isNotEmpty() && confirmPassword != password.value){
            hasError = true
            currentState = currentState.copy(
                confirmPassword = currentState.confirmPassword.copy(
                    errorMessage = "* A senha e a confirmação devem ser iguais"
                )
            )
        }

        if(!hasError){
           currentState = currentState.copy(
               state = UiStateView.Success(true)
           )
        }

        _RegisterUiState.update {
            currentState
        }
    }


    fun onEmailChanged(value: String) {

        _RegisterUiState.update { currentState ->
            currentState.copy(
                email = currentState.email.copy(value = value, errorMessage = "")
            )
        }
    }

    fun onUserNameChanged(value: String) {
        _RegisterUiState.update { currentState ->
            currentState.copy(
                userName = currentState.userName.copy(value = value, errorMessage = "")
            )
        }
    }

    fun onPasswordChanged(value: String) {
        val currentState = _RegisterUiState.value
        var validationList: List<ValidationModel> = currentState.password.onChangeValidation

        if(validationList.isEmpty()){
            validationList = getPasswordValidationItemList()
        }

        if (value.isNotEmpty()) {
            validationList = validateList(value, validationList)
        }

        val newState = currentState.copy(
            password = currentState.password.copy(
                value = value,
                onChangeValidation = validationList,
                errorMessage = ""
            )
        )

        _RegisterUiState.update { currentState ->
            newState
        }
    }

    fun onConfirmPasswordChanged(value: String) {
        _RegisterUiState.update { currentState ->
            currentState.copy(
                confirmPassword = currentState.confirmPassword.copy(value = value, errorMessage = "")
            )
        }
    }

    private fun validateList(
        value: String,
        validationModelList: List<ValidationModel>
    ): List<ValidationModel> {
        val updateValidationItemList: MutableList<ValidationModel> = mutableListOf()

        validationModelList.forEach { validationModel ->
            val isValid = ValidationInput.getValidation(value, validationModel.validationType)
            val newValidationModel = validationModel

            newValidationModel.isValid = isValid
            updateValidationItemList.add(newValidationModel)
        }

        return updateValidationItemList
    }

}