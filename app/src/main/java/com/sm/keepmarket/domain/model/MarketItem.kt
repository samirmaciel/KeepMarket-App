package com.sm.keepmarket.domain.model

import java.math.BigDecimal
import java.text.NumberFormat
import java.time.LocalDateTime
import java.util.Locale

class MarketItem(val id: String, val marketId: String, val name: String, val createdDate: LocalDateTime){

    private var priceTotal: BigDecimal = BigDecimal.ZERO
    var price: BigDecimal = BigDecimal.ZERO
    var amount: Int = 0
    var isChecked: Boolean = false

    fun getTotalPrice() : BigDecimal{
        return BigDecimal(amount).multiply(price)
    }

    fun getFormattedPrice() = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(price)

    fun getFormattedTotalPrice() = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(getTotalPrice())
}
