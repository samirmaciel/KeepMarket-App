package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.ProductEntity
import kotlinx.coroutines.flow.Flow

interface IProductDatasource {

    suspend fun getByID(id: String, userID: String): Flow<ProductEntity?>
    suspend fun getLast(name: String, userID: String): Flow<ProductEntity?>
    suspend fun insert(productEntity: ProductEntity, userID: String)
    suspend fun delete(id: String, userID: String)
    suspend fun getAllByMarketItemParent(marketItemParent: String, userID: String): Flow<List<ProductEntity>>
}