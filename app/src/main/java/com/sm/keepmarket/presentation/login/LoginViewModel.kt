package com.sm.keepmarket.presentation.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.ILoginRepository
import com.sm.keepmarket.util.UiStateView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: ILoginRepository): ViewModel() {
    private val _LoginUIState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val loginUiState = _LoginUIState.asStateFlow()


    fun onUsernameChanged(value: String){
        _LoginUIState.update {
            it.copy(
                userName = InputState(value = value)
            )
        }

        _LoginUIState.update { it.copy(state = UiStateView.Idle) }
    }

    fun onPasswordUsernameChanged(value: String){
        _LoginUIState.update {
            it.copy(
                password = InputState(value = value)
            )
        }

        _LoginUIState.update { it.copy(state = UiStateView.Idle) }
    }

    fun getCurrentLogin(){
        viewModelScope.launch {
            loginRepository.getCurrentLogin().collect {
                it?.let { loginModel ->
                    _LoginUIState.update {
                        it.copy(
                            state = UiStateView.Success(true)
                        )
                    }
                } ?: run {
                    _LoginUIState.update {
                        it.copy(
                            state = UiStateView.Success(false)
                        )
                    }
                }
            }
        }
    }

    fun login(){

        val userName = _LoginUIState.value.userName.value
        val password = _LoginUIState.value.password.value

        if(userName.isEmpty()){
            _LoginUIState.update {
                val userNameInputState = it.userName
                it.copy(
                    userName = userNameInputState.copy(hasError = true, errorMessage = "Campo obrigatório")
                )
            }
        }

        if(password.isEmpty()){
            _LoginUIState.update {
                val passwordInputState = it.password
                it.copy(
                    password = passwordInputState.copy(hasError = true, errorMessage = "Campo obrigatório")
                )
            }
        }

        if(!_LoginUIState.value.userName.hasError && !_LoginUIState.value.password.hasError ){
            viewModelScope.launch {
                val loginModel = loginRepository.getValidateLogin(userName, password).first()
                var uiState: UiStateView<Boolean> = UiStateView.Error("User name ou password incorretos")

                loginModel?.let {
                    uiState = UiStateView.Success(true)
                }

                _LoginUIState.update {
                    it.copy(
                        state = uiState
                    )
                }
            }
        }
    }


}