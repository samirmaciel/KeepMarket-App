package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.domain.model.MarketItem
import kotlinx.coroutines.flow.Flow

interface IMarketItemRepository {

    suspend fun getAll(): Flow<List<MarketItem>>
    suspend fun getAllByOwner(ownerId: String): Flow<List<MarketItem>>
    suspend fun getById(id: String): Flow<MarketItem?>
    suspend fun delete(marketItem: MarketItem)
    suspend fun insert(marketItem: MarketItem)
}