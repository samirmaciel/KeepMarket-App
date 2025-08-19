package com.sm.keepmarket.domain

data class Market(
    val id: String,
    val name: String,
    var items: List<MarketItem> = emptyList()
)
