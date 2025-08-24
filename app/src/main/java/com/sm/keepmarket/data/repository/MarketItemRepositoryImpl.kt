package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemDatasource
import com.sm.keepmarket.data.mapper.MarketItemMapper
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemRepository
import com.sm.keepmarket.domain.model.MarketItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MarketItemRepositoryImpl(private val marketItemDatasource: IMarketItemDatasource) :
    IMarketItemRepository {

    override suspend fun getAll(): Flow<List<MarketItem>> {
        return flow {
            marketItemDatasource.getAll().collect { marketItemEntityList ->
                emit(marketItemEntityList.map { MarketItemMapper.toMarketItem(it) })
            }
        }
    }

    override suspend fun getAllByOwner(ownerId: String): Flow<List<MarketItem>> {
        return flow {
            marketItemDatasource.getAllByOwner(ownerId).collect { marketItemEntityList ->
                emit(marketItemEntityList.map { MarketItemMapper.toMarketItem(it) })
            }
        }
    }

    override suspend fun getById(id: String): Flow<MarketItem?> {
        return flow {
            marketItemDatasource.getById(id).collect { marketItemEntity ->
                marketItemEntity?.let {
                    emit(MarketItemMapper.toMarketItem(it))
                }.run {
                    emit(null)
                }
            }
        }
    }

    override suspend fun delete(marketItem: MarketItem) {
        marketItemDatasource.delete(MarketItemMapper.toEntity(marketItem))
    }

    override suspend fun insert(marketItem: MarketItem) {
        marketItemDatasource.insert(MarketItemMapper.toEntity(marketItem))
    }
}