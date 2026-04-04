package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.data.model.ProductEntity
import com.sm.keepmarket.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface IProductRepository {

    suspend fun getByID(id: String): Flow<ProductModel?>
    suspend fun getLast(name: String): Flow<ProductModel?>
    suspend fun insert(productModel: ProductModel)
    suspend fun delete(id: String)
    suspend fun getAllByMarketItemParent(marketItemParent: String): Flow<List<ProductModel>>

}