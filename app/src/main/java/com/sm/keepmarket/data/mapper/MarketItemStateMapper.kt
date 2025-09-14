package com.sm.keepmarket.data.mapper

import com.sm.keepmarket.data.model.MarketItemStateEntity
import com.sm.keepmarket.domain.model.MarketItemState

object MarketItemStateMapper {

    fun toEntity(marketItemState: MarketItemState): MarketItemStateEntity {
        return MarketItemStateEntity(
            id = marketItemState.id,
            marketId = marketItemState.marketId,
            marketItemId = marketItemState.marketItemId,
            name = marketItemState.name,
            price = marketItemState.price,
            amount = marketItemState.amount,
            isChecked = marketItemState.isChecked,
            lastUpdate = marketItemState.lastUpdate,
            createdDate = marketItemState.createdDate,
            enabled = marketItemState.enabled
        )
    }

    fun toModel(marketItemStateEntity: MarketItemStateEntity): MarketItemState {
        return MarketItemState(
            id = marketItemStateEntity.id,
            marketId = marketItemStateEntity.marketId,
            marketItemId = marketItemStateEntity.marketItemId,
            name = marketItemStateEntity.name,
            price = marketItemStateEntity.price,
            amount = marketItemStateEntity.amount,
            isChecked = marketItemStateEntity.isChecked,
            lastUpdate = marketItemStateEntity.lastUpdate,
            createdDate = marketItemStateEntity.createdDate,
            enabled = marketItemStateEntity.enabled)
    }
}