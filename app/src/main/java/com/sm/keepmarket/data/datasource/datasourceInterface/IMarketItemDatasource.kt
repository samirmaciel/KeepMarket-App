package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.MarketItemEntity
import kotlinx.coroutines.flow.Flow

interface IMarketItemDatasource {

    suspend fun getAllByUserID(userID: String): Flow<List<MarketItemEntity>>
    suspend fun getAllByMarketID(marketID: String, userID: String): Flow<List<MarketItemEntity>>
    suspend fun getById(marketItemID: String, userID: String): Flow<MarketItemEntity?>
    suspend fun deleteByID(marketItemID: String, userID: String)
    suspend fun deleteAllByMarketID(marketID: String, userID: String)
    suspend fun insert(marketItemEntity: MarketItemEntity, userID: String)
}