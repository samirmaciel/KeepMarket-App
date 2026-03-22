package com.sm.keepmarket.domain.model

import android.util.Log
import java.math.BigDecimal
import java.text.NumberFormat
import java.time.LocalDateTime
import java.util.Locale
import java.util.UUID

data class MarketItem(
    val id: String,
    val marketId: String,
    val name: String,
    val productName: String,
    val createdDate: LocalDateTime,
    val price: BigDecimal = BigDecimal.ZERO,
    val amount: Int = 0,
    val isChecked: Boolean = false
) {
    fun getTotalPrice(): BigDecimal = BigDecimal(amount).multiply(price)

    fun getFormattedPrice() = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(price)

    fun getFormattedTotalPrice() = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(getTotalPrice())
}

fun MarketItem.toItemState(): MarketItemState {
    return MarketItemState(
        id = UUID.randomUUID().toString(),
        marketItemId = id,
        marketId = marketId,
        name = name,
        productName = productName,
        createdDate = LocalDateTime.now(),
        price = price,
        amount = amount,
        isChecked = isChecked,
        lastUpdate = LocalDateTime.now(),
        enabled = true
    )
}
