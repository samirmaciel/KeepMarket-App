package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IProductDatasource
import com.sm.keepmarket.data.model.toProductModel
import com.sm.keepmarket.data.repository.repositoryInterface.IProductRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IUserRepository
import com.sm.keepmarket.domain.model.ProductModel
import com.sm.keepmarket.domain.model.toProductEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl(private val productDatasource : IProductDatasource, private val userRepository: IUserRepository) : IProductRepository {

    override suspend fun getByID(id: String): Flow<ProductModel?> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            productDatasource.getByID(id, userID).collect { productEntity ->
                productEntity?.let {
                    emit( productEntity.toProductModel())
                }?.run {
                    emit(null)
                }
            }
        }.catch { e -> emit(null) }
    }

    override suspend fun getLast(name: String): Flow<ProductModel?> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            productDatasource.getLast(name, userID).collect { productEntity ->
                productEntity?.let {
                    emit( productEntity.toProductModel())
                }?.run {
                    emit(null)
                }
            }
        }.catch { e -> emit(null) }
    }

    override suspend fun insert(
        productModel: ProductModel
    ) {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        productDatasource.insert(productModel.toProductEntity(), userID)
    }

    override suspend fun delete(id: String) {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        productDatasource.delete(id, userID)
    }

    override suspend fun getAllByMarketItemParent(marketItemParent: String): Flow<List<ProductModel>> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            productDatasource.getAllByMarketItemParent(marketItemParent, userID).collect { productEntityList ->
                emit(productEntityList.map { it.toProductModel() })
            }
        }.catch { e ->
            emit(emptyList())
        }
    }
}