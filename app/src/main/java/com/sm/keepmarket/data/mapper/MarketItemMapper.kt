package com.sm.keepmarket.data.mapper

import com.google.firebase.Timestamp
import com.sm.keepmarket.data.model.MarketItemEntity
import com.sm.keepmarket.domain.model.MarketItem
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

object MarketItemMapper {

    fun toMarketItem(entity: MarketItemEntity): MarketItem {

        val createdDateTime = entity.createdDate?.toDate()
            ?.toInstant()
            ?.atZone(ZoneId.systemDefault())
            ?.toLocalDateTime()

        return MarketItem(
            id = entity.id!!,
            marketId = entity.ownerID!!,
            name = entity.name!!,
            createdDate = createdDateTime!!
        )
    }

    fun toEntity(marketItem: MarketItem): MarketItemEntity {

        val createdDate = Timestamp(
            Date.from(
                marketItem.createdDate.atZone(ZoneId.systemDefault()).toInstant()
            ))

        return MarketItemEntity(
            id = marketItem.id,
            ownerID = marketItem.marketId,
            name = marketItem.name,
            createdDate = createdDate
        )
    }

}