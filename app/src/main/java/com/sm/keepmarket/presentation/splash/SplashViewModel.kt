package com.sm.keepmarket.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sm.keepmarket.data.repository.repositoryInterface.ILoginRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IUserRepository
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.Pantry
import com.sm.keepmarket.util.UiStateView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class SplashViewModel(
    private val userDataRepository: IUserRepository
) : ViewModel() {

    private val _SplashState: MutableStateFlow<UiStateView<Boolean>> =
        MutableStateFlow(UiStateView.Loading)
    val splashState = _SplashState.asStateFlow()


    fun getCurrentUser(){
        viewModelScope.launch {
            userDataRepository.getCurrentUser().collect {
                it?.let {
                    _SplashState.update {
                        UiStateView.Success(true)
                    }
                } ?: run {
                    _SplashState.update {
                        UiStateView.Success(false)
                    }
                }
            }
        }
    }
}