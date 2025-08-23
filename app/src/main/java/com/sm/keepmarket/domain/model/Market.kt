package com.sm.keepmarket.domain.model

import java.time.LocalDateTime

data class Market(
    val id: String,
    val name: String,
    val createdDate: LocalDateTime,
    val lastUpdate: LocalDateTime,
    var items: List<MarketItem> = emptyList()
)
