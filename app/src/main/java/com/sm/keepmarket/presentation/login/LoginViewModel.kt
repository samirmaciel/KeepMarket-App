package com.sm.keepmarket.presentation.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.ILoginRepository
import com.sm.keepmarket.domain.model.LoginModel
import com.sm.keepmarket.util.UiStateView
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class LoginViewModel(private val loginRepository: ILoginRepository): ViewModel() {

    private val _MainUiState: MutableStateFlow<UiStateView<LoginModel>> = MutableStateFlow(UiStateView.Loading)

    val mainUiState = _MainUiState.asStateFlow()

    fun getCurrentLogin(){
        viewModelScope.launch {
            loginRepository.getCurrentLogin().collect {
                it?.let { loginModel ->
                    _MainUiState.update {
                        UiStateView.Success(loginModel)
                    }
                } ?: run {
                    _MainUiState.update {
                        UiStateView.Error("Current login not founded")
                    }
                }
            }
        }
    }

    fun validateInput(login: String, password: String){

        if(login.isNotEmpty() && password.isNotEmpty()){

            val loginModel = LoginModel(UUID.randomUUID().toString(), login, password,
                LocalDateTime.now(), true)
            _MainUiState.update {
                UiStateView.Success(loginModel)
            }
        }
    }


}