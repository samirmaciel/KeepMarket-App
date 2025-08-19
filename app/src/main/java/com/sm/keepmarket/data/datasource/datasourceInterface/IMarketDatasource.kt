package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.MarketEntity
import kotlinx.coroutines.flow.Flow

interface IMarketDatasource {

    suspend fun getAll(): Flow<List<MarketEntity>>
    suspend fun getById(id: String): Flow<MarketEntity?>
    suspend fun delete(marketEntity: MarketEntity)
    suspend fun insert(marketEntity: MarketEntity)
}