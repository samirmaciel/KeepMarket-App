package com.sm.keepmarket.domain.model

import java.time.LocalDateTime

data class Market(
    val id: String,
    val name: String,
    val createdDate: LocalDateTime,
    var lastUpdate: LocalDateTime,
    var items: List<MarketItem> = emptyList()
)
