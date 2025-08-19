package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketDatasource
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.domain.Market
import kotlinx.coroutines.flow.Flow

class MarketRepositoryImpl(marketDatasource: IMarketDatasource) : IMarketRepository {

    override suspend fun getAll(): Flow<List<Market>> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): Flow<Market?> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(market: Market) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(market: Market) {
        TODO("Not yet implemented")
    }
}