package com.sm.keepmarket.data.mapper

import com.sm.keepmarket.data.model.MarketEntity
import com.sm.keepmarket.domain.model.Market

object MarketMapper {

    fun toMarket(entity: MarketEntity): Market {
        return Market(
            id = entity.id,
            name = entity.name,
            createdDate = entity.createdDate,
            lastUpdate = entity.lastUpdate
        )
    }

    fun toEntity(market: Market): MarketEntity{
        return MarketEntity(
            id = market.id,
            name = market.name,
            createdDate = market.createdDate,
            lastUpdate = market.lastUpdate
        )
    }
}