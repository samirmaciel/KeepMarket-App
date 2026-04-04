package com.sm.keepmarket.data.mapper

import com.google.firebase.Timestamp
import com.sm.keepmarket.data.model.MarketItemStateEntity
import com.sm.keepmarket.domain.model.MarketItemState
import com.sm.keepmarket.util.ProductUnitType
import java.time.ZoneId
import java.util.Date

object MarketItemStateMapper {

    fun toEntity(marketItemState: MarketItemState): MarketItemStateEntity {

        val createdDate = Timestamp(
            Date.from(
                marketItemState.createdDate.atZone(ZoneId.systemDefault()).toInstant()
            ))

        val lastUpdate = Timestamp(
            Date.from(
                marketItemState.lastUpdate.atZone(ZoneId.systemDefault()).toInstant()
            )
        )

        return MarketItemStateEntity(
            id = marketItemState.id,
            marketId = marketItemState.marketId,
            marketItemId = marketItemState.marketItemId,
            name = marketItemState.name,
            productName = marketItemState.productName,
            price = marketItemState.price.toPlainString(),
            amount = marketItemState.amount,
            unitType = marketItemState.unitType.value,
            checked = marketItemState.isChecked,
            lastUpdate = lastUpdate,
            createdDate = createdDate,
        )
    }

    fun toModel(marketItemStateEntity: MarketItemStateEntity): MarketItemState {


        val createdDateTime = marketItemStateEntity.createdDate?.toDate()
            ?.toInstant()
            ?.atZone(ZoneId.systemDefault())
            ?.toLocalDateTime()

        val lastUpdate = marketItemStateEntity.createdDate?.toDate()
            ?.toInstant()
            ?.atZone(ZoneId.systemDefault())
            ?.toLocalDateTime()

        return MarketItemState(
            id = marketItemStateEntity.id!!,
            marketId = marketItemStateEntity.marketId!!,
            marketItemId = marketItemStateEntity.marketItemId!!,
            name = marketItemStateEntity.name!!,
            productName = marketItemStateEntity.productName!!,
            price = marketItemStateEntity.price!!.toBigDecimal(),
            amount = marketItemStateEntity.amount!!,
            unitType = ProductUnitType.valueOf(marketItemStateEntity.unitType!!),
            isChecked = marketItemStateEntity.checked!!,
            lastUpdate = lastUpdate!!,
            createdDate = createdDateTime!!
        )
    }
}