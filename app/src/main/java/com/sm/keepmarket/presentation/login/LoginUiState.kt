package com.sm.keepmarket.presentation.login
import com.sm.keepmarket.util.UiStateView

data class LoginUiState(
    val userName: InputState<String> = InputState(value = ""),
    val password: InputState<String> = InputState(value = ""),
    val state: UiStateView<Boolean> = UiStateView.Loading
)

data class InputState<T>(
    val value: T,
    val hasError: Boolean = false,
    val errorMessage: String = ""
)