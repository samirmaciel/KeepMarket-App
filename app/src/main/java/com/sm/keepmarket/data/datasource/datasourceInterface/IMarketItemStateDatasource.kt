package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.MarketItemStateEntity
import kotlinx.coroutines.flow.Flow

interface IMarketItemStateDatasource {

    fun getAll(): Flow<List<MarketItemStateEntity>>
    fun insert(item: MarketItemStateEntity)
    fun delete(item: MarketItemStateEntity)
    fun getByID(id: String): Flow<MarketItemStateEntity?>
    fun getByOwnerID(ownerID: String): Flow<List<MarketItemStateEntity>>
}