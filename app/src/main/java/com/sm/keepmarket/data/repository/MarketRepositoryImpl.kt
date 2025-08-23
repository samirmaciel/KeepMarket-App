package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketDatasource
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.util.Mock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MarketRepositoryImpl(marketDatasource: IMarketDatasource) : IMarketRepository {

    override suspend fun getAll(): Flow<List<Market>> {
        return flow {
            emit(Mock.getMarketList())
        }
    }

    override suspend fun getById(id: String): Flow<Market?> {
        return flow {
            emit(Mock.getMarketList().first())
        }
    }

    override suspend fun delete(market: Market) {

    }

    override suspend fun insert(market: Market) {

    }
}