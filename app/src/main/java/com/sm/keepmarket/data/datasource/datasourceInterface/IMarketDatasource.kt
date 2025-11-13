package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.MarketEntity
import kotlinx.coroutines.flow.Flow

interface IMarketDatasource {

    suspend fun getAllByUserID(userID: String): Flow<List<MarketEntity>>
    suspend fun getById(userID: String, marketID: String): Flow<MarketEntity?>
    suspend fun deleteByID(userID: String,marketID: String)
    suspend fun deleteAllByUserID(userID: String)
    suspend fun insert(userID: String, marketEntity: MarketEntity)
}