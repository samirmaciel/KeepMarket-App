package com.sm.keepmarket.data.mapper

import com.sm.keepmarket.data.model.MarketItemEntity
import com.sm.keepmarket.domain.model.MarketItem

class MarketItemMapper() {

    fun toMarketItem(entity: MarketItemEntity): MarketItem {
        return MarketItem(
            id = entity.id,
            marketId = entity.ownerID,
            name = entity.name,
            createdDate = entity.createdDate
        )
    }

    fun toEntity(marketItem: MarketItem): MarketItemEntity {
        return MarketItemEntity(
            id = marketItem.id,
            ownerID = marketItem.marketId,
            name = marketItem.name,
            createdDate = marketItem.createdDate
        )
    }

}