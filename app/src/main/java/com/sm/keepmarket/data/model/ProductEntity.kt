package com.sm.keepmarket.data.model

import com.google.firebase.Timestamp
import com.sm.keepmarket.domain.model.ProductModel
import com.sm.keepmarket.util.ProductUnitType
import java.time.ZoneId

data class ProductEntity(
    val id: String? = null,
    val name: String? = null,
    val marketItemParent: String? = null,
    val price: String? = null,
    val amount: Double? = null,
    val unitType: String? = null,
    val createdDate: Timestamp? = null
)

fun ProductEntity.toProductModel(): ProductModel {

    val createdDateTime = this.createdDate?.toDate()
        ?.toInstant()
        ?.atZone(ZoneId.systemDefault())
        ?.toLocalDateTime()

    return ProductModel(
        id = this.id!!,
        name = this.name!!,
        marketItemParent = this.marketItemParent!!,
        price = this.price?.toBigDecimal()!!,
        amount = this.amount!!,
        unitType = ProductUnitType.valueOf(this.unitType!!),
        createdDate = createdDateTime!!
    )
}
