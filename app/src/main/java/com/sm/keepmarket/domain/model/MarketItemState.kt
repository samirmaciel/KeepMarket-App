package com.sm.keepmarket.domain.model

import java.math.BigDecimal
import java.time.LocalDateTime


data class MarketItemState(
    val id: String,
    val marketId: String,
    val marketItemId: String,
    val name: String,
    val productName: String,
    val price: BigDecimal,
    val amount: Int,
    val isChecked: Boolean,
    val lastUpdate: LocalDateTime,
    val createdDate: LocalDateTime,
    val enabled: Boolean
)
