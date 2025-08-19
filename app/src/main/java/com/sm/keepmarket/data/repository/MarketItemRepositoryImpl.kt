package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemDatasource
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemRepository
import com.sm.keepmarket.domain.MarketItem
import kotlinx.coroutines.flow.Flow

class MarketItemRepositoryImpl(marketItemDatasource: IMarketItemDatasource) :
    IMarketItemRepository {

    override suspend fun getAll(): Flow<List<MarketItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllByOwner(ownerId: String): Flow<List<MarketItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): Flow<MarketItem?> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(marketItem: MarketItem) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(marketItem: MarketItem) {
        TODO("Not yet implemented")
    }
}