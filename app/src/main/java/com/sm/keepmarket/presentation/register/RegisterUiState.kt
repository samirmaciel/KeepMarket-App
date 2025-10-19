package com.sm.keepmarket.presentation.register

import com.sm.keepmarket.presentation.login.InputState
import com.sm.keepmarket.util.UiStateView

data class RegisterUiState(
    val email: InputState<String> = InputState(value = ""),
    val userName: InputState<String> = InputState(value = ""),
    val password: InputState<String> = InputState(value = ""),
    val confirmPassword: InputState<String> = InputState(value = ""),
    var state: UiStateView<Boolean> = UiStateView.Loading
)
