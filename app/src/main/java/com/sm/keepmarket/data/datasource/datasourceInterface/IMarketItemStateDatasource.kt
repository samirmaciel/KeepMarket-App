package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.MarketItemStateEntity
import kotlinx.coroutines.flow.Flow

interface IMarketItemStateDatasource {

    suspend fun insert(item: MarketItemStateEntity)
    suspend fun delete(item: MarketItemStateEntity)
    suspend fun getByID(id: String): Flow<MarketItemStateEntity?>
    suspend fun getAllByMarketID(marketID: String): Flow<List<MarketItemStateEntity>>
}