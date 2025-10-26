package com.sm.keepmarket.presentation.login


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.sm.keepmarket.data.repository.repositoryInterface.ILoginRepository
import com.sm.keepmarket.domain.model.UserLoginModel
import com.sm.keepmarket.util.UiStateView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: ILoginRepository): ViewModel() {
    private val _LoginUIState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val loginUiState = _LoginUIState.asStateFlow()


    init {

    }


    fun onUsernameChanged(value: String){
        _LoginUIState.update {
            it.copy(
                email = InputState(value = value)
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
            loginRepository.getCurrentUser().collect {
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

        val email = _LoginUIState.value.email.value
        val password = _LoginUIState.value.password.value

        if(email.isEmpty()){
            _LoginUIState.update {
                val userNameInputState = it.email
                it.copy(
                    email = userNameInputState.copy(errorMessage = "Campo obrigatório")
                )
            }
        }

        if(password.isEmpty()){
            _LoginUIState.update {
                val passwordInputState = it.password
                it.copy(
                    password = passwordInputState.copy(errorMessage = "Campo obrigatório")
                )
            }
        }

        if(_LoginUIState.value.email.errorMessage.isEmpty() && _LoginUIState.value.password.errorMessage.isEmpty() ){
            viewModelScope.launch {
                val loginModel = loginRepository.makeLogin(UserLoginModel(email, password)).firstOrNull()
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