package com.sm.keepmarket.presentation.register

import androidx.lifecycle.ViewModel
import com.sm.keepmarket.domain.model.LoginModel
import com.sm.keepmarket.domain.model.ValidationInput
import com.sm.keepmarket.domain.model.ValidationModel
import com.sm.keepmarket.util.UiStateView
import com.sm.keepmarket.util.ValidationType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {

    private val _UiState: MutableStateFlow<UiStateView<LoginModel>> =
        MutableStateFlow(UiStateView.Loading)
    private val _PasswordValidationState: MutableStateFlow<UiStateView<List<ValidationModel>>> =
        MutableStateFlow(UiStateView.Loading)
    val uiState = _UiState.asStateFlow()
    val passwordValidationItemListState = _PasswordValidationState.asStateFlow()

    private fun getPasswordValidationItemList() : List<ValidationModel> {
        val validationItemList = listOf(
            ValidationModel(
                id = "1",
                validationType = ValidationType.MORE_OR_EQUAL_THAN_8_CHARACTERS,
                "Maior ou igual a 8 caracteres"
            ),
            ValidationModel(
                id = "2",
                validationType = ValidationType.ONE_CAPITAL_LETTER,
                "Pelo menos uma letra maiúscula"
            )
        )

        return validationItemList
    }

    private fun registerUser(loginModel: LoginModel) {

    }

    fun validatePassword(value: String) {

        var currentPasswordValidationItemList: List<ValidationModel>? = null

        if(_PasswordValidationState.value is UiStateView.Success){
            currentPasswordValidationItemList = (_PasswordValidationState.value as UiStateView.Success<List<ValidationModel>>).data
        }

        if(currentPasswordValidationItemList == null){
            currentPasswordValidationItemList = getPasswordValidationItemList()
        }

        val updateValidationItemList = updateValidationList(value, currentPasswordValidationItemList)

        _PasswordValidationState.update {
            UiStateView.Success(updateValidationItemList)
        }
    }

    private fun updateValidationList(value: String, currentValidationModelList: List<ValidationModel>): List<ValidationModel>{
        val updateValidationItemList: MutableList<ValidationModel> = mutableListOf()

        currentValidationModelList.forEach { validationModel ->

            val isValid = ValidationInput.getValidation(value, validationModel.validationType)

            val newValidationModel = validationModel
            newValidationModel.isValid = isValid
            updateValidationItemList.add(newValidationModel)
        }

        return updateValidationItemList
    }

    fun clearPasswordValidation(){
        _PasswordValidationState.update {
            UiStateView.Loading
        }
    }

    fun allPasswordValidationIsValid(): Boolean {
        if(_PasswordValidationState.value is UiStateView.Success){
            var isValid = true

            for(validationModel in (_PasswordValidationState.value as UiStateView.Success<List<ValidationModel>>).data) {
                if(!validationModel.isValid){
                    isValid = false
                    break
                }
            }

            return isValid
        }

        return true

    }


}