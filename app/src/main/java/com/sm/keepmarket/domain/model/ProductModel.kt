package com.sm.keepmarket.domain.model

import com.google.firebase.Timestamp
import com.sm.keepmarket.data.model.ProductEntity
import com.sm.keepmarket.util.ProductUnitType
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

data class ProductModel(
    val id: String,
    val name: String,
    val marketItemParent: String,
    val price: BigDecimal,
    val amount: Double,
    val unitType: ProductUnitType,
    val createdDate: LocalDateTime
)

fun ProductModel.toProductEntity() : ProductEntity {

    val createdDate = Timestamp(
        Date.from(
            this.createdDate.atZone(ZoneId.systemDefault()).toInstant()
        ))

    return ProductEntity(
        id = this.id,
        name = this.name,
        marketItemParent = this.marketItemParent,
        price = this.price.toPlainString(),
        amount = this.amount,
        unitType = this.unitType.value,
        createdDate = createdDate
    )
}
