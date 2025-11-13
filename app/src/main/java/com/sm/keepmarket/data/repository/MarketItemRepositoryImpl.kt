package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemDatasource
import com.sm.keepmarket.data.mapper.MarketItemMapper
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IUserRepository
import com.sm.keepmarket.domain.model.MarketItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class MarketItemRepositoryImpl(private val marketItemDatasource: IMarketItemDatasource, private val userRepository: IUserRepository) :
    IMarketItemRepository {

    override suspend fun getAll(): Flow<List<MarketItem>> {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            marketItemDatasource.getAllByUserID(userID).collect { marketItemEntityList ->
                emit(marketItemEntityList.map { MarketItemMapper.toMarketItem(it) })
            }
        }
    }

    override suspend fun getAllByOwner(ownerId: String): Flow<List<MarketItem>> {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }
            marketItemDatasource.getAllByMarketID(ownerId, userID).collect { marketItemEntityList ->
                emit(marketItemEntityList.map { MarketItemMapper.toMarketItem(it) })
            }
        }
    }

    override suspend fun getById(id: String): Flow<MarketItem?> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            marketItemDatasource.getById(id, userID).collect { marketItemEntity ->
                marketItemEntity?.let {
                    emit(MarketItemMapper.toMarketItem(it))
                }.run {
                    emit(null)
                }
            }
        }
    }

    override suspend fun delete(marketItem: MarketItem) {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        marketItemDatasource.deleteByID(marketItem.id, userID)
    }

    override suspend fun insert(marketItem: MarketItem) {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        marketItemDatasource.insert(MarketItemMapper.toEntity(marketItem), userID)
    }
}