package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.domain.model.MarketItemState
import kotlinx.coroutines.flow.Flow

interface IMarketItemStateRepository {

    suspend fun insert(marketItemState: MarketItemState)
    suspend fun delete(marketItemState: MarketItemState)
    suspend fun getAllByMarketId(marketId: String): Flow<List<MarketItemState>>
}