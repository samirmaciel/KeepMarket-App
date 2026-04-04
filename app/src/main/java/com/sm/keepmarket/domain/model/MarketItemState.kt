package com.sm.keepmarket.domain.model

import com.sm.keepmarket.util.ProductUnitType
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID


data class MarketItemState(
    val id: String,
    val marketId: String,
    val marketItemId: String,
    val name: String,
    val productName: String,
    val price: BigDecimal,
    val amount: Double,
    val unitType: ProductUnitType,
    val isChecked: Boolean,
    val lastUpdate: LocalDateTime,
    val createdDate: LocalDateTime,
)

fun MarketItemState.toProductModel(): ProductModel {
    return ProductModel(
        id = UUID.randomUUID().toString(),
        name = this.productName,
        marketItemParent = this.name,
        price = this.price,
        amount = this.amount,
        unitType = this.unitType,
        createdDate = LocalDateTime.now()
    )
}
