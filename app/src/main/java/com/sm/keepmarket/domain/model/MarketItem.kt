package com.sm.keepmarket.domain.model

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class MarketItem(val id: String, val ownerId: String, val name: String){

    private var priceTotal: BigDecimal = BigDecimal.ZERO
    var price: BigDecimal = BigDecimal.ZERO
    var amount: Int = 0
    var isChecked: Boolean = false

    fun getPriceTotal() : BigDecimal{
        return BigDecimal(amount).multiply(price)
    }

    fun getFormattedPrice() = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(price)

    fun getFormattedTotalPrice() = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(getPriceTotal())
}
