package com.sm.keepmarket.presentation.pantryList

import com.sm.keepmarket.domain.model.Pantry
import com.sm.keepmarket.domain.model.PantryItem
import com.sm.keepmarket.util.UiState

data class PantryListUiState (
    val pantry: Pantry? = null,
    val state: UiState = UiState.LOADED,
    val lastDeleteItem: PantryItem? = null
)
