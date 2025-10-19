package com.sm.keepmarket.presentation.login
import com.sm.keepmarket.domain.model.ValidationModel
import com.sm.keepmarket.util.UiStateView

data class LoginUiState(
    val userName: InputState<String> = InputState(value = ""),
    val password: InputState<String> = InputState(value = ""),
    val state: UiStateView<Boolean> = UiStateView.Loading
)

data class InputState<T>(
    val value: T,
    val onChangeValidation: List<ValidationModel> = emptyList(),
    val errorMessage: String = ""
)