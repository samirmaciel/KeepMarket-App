package com.sm.keepmarket.data.mapper

import com.google.firebase.Timestamp
import com.sm.keepmarket.data.model.MarketEntity
import com.sm.keepmarket.domain.model.Market
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

object MarketMapper {

    fun toMarket(entity: MarketEntity): Market {

        val createdDateTime = entity.createdDate?.toDate()
            ?.toInstant()
            ?.atZone(ZoneId.systemDefault())
            ?.toLocalDateTime()

        val lastUpdate = entity.createdDate?.toDate()
            ?.toInstant()
            ?.atZone(ZoneId.systemDefault())
            ?.toLocalDateTime()


        return Market(
            id = entity.id!!,
            name = entity.name!!,
            createdDate = createdDateTime!!,
            lastUpdate = lastUpdate!!
        )
    }

    fun toEntity(market: Market): MarketEntity{

        val createdDate = Timestamp(
            Date.from(
                market.createdDate.atZone(ZoneId.systemDefault()).toInstant()
            ))

        val lastUpdate = Timestamp(
            Date.from(
                market.lastUpdate.atZone(ZoneId.systemDefault()).toInstant()
            )
        )


        return MarketEntity(
            id = market.id,
            name = market.name,
            createdDate = createdDate,
            lastUpdate = lastUpdate
        )
    }
}