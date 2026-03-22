package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.MarketItemStateEntity
import kotlinx.coroutines.flow.Flow

interface IMarketItemStateDatasource {

    suspend fun getAllByMarketID(marketID: String, userID: String): Flow<List<MarketItemStateEntity>>
    suspend fun getByID(marketItemStateID: String, userID: String): Flow<MarketItemStateEntity?>
    suspend fun insert(marketItemStateEntity: MarketItemStateEntity, userID: String)
    suspend fun deleteByID( marketItemStateID: String, userID: String)
    suspend fun deleteAllByMarketID(marketID: String, userID: String)
    suspend fun getLastByName(name: String, userID: String): Flow<MarketItemStateEntity?>
    suspend fun getLastByProductName(productName: String, userID: String): Flow<MarketItemStateEntity?>

}