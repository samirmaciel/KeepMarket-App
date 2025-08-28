package com.sm.keepmarket.presentation.home

import com.sm.keepmarket.domain.model.FeaturedCard
import com.sm.keepmarket.domain.model.Highlight
import com.sm.keepmarket.util.UiState

data class HomeViewUiState(
    val featuredCardList: List<FeaturedCard> = emptyList(),
    val highlightList: List<Highlight> = emptyList(),
    val state: UiState = UiState.LOADED
)
