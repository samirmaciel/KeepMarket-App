package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.MarketItemEntity
import kotlinx.coroutines.flow.Flow

interface IMarketItemDatasource {

    suspend fun getAll(): Flow<List<MarketItemEntity>>
    suspend fun getAllByOwner(ownerId: String): Flow<List<MarketItemEntity>>
    suspend fun getById(id: String): Flow<MarketItemEntity?>
    suspend fun delete(marketItemEntity: MarketItemEntity)
    suspend fun insert(marketItemEntity: MarketItemEntity)
}