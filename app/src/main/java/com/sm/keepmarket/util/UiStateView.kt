package com.sm.keepmarket.util

sealed class UiStateView<out T> {
    object Loading : UiStateView<Nothing>()
    data class Success<T>(val data: T) : UiStateView<T>()
    data class Error(val message: String) : UiStateView<Nothing>()
}