package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemStateDatasource
import com.sm.keepmarket.data.db.dao.MarketItemStateDao
import com.sm.keepmarket.data.model.MarketItemStateEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MarketItemStateDatasourceImpl(private val dao: MarketItemStateDao) : IMarketItemStateDatasource {

    override suspend fun insert(item: MarketItemStateEntity) {
        dao.insert(item)
    }

    override suspend fun delete(item: MarketItemStateEntity) {
       dao.delete(item)
    }

    override suspend fun getByID(id: String): Flow<MarketItemStateEntity?> {
        return flow {
            emit(dao.getByID(id))
        }
    }

    override suspend fun getLastByName(name: String): Flow<MarketItemStateEntity?> {
        return flow {
            emit(dao.getLastByName(name))
        }
    }

    override suspend fun getAllByMarketID(marketID: String): Flow<List<MarketItemStateEntity>> {
        return flow{
            emit(dao.getByOwnerID(marketID))
        }
    }
}