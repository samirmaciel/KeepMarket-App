package com.sm.keepmarket.presentation.marketList

import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.MarketItem
import com.sm.keepmarket.util.UiState

data class MarketListUiState (
    val market: Market? = null,
    val state: UiState = UiState.LOADED,
    val lastDeleteItem: MarketItem? = null
)