package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketDatasource
import com.sm.keepmarket.data.db.dao.MarketDao
import com.sm.keepmarket.data.model.MarketEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MarketDatasourceImpl(private val dao: MarketDao): IMarketDatasource {

    override suspend fun getAll(): Flow<List<MarketEntity>> {
        return flow {
            emit(dao.getAll())

        }
    }

    override suspend fun getById(id: String): Flow<MarketEntity?> {
        return flow {
            emit(dao.getById(id))
        }
    }

    override suspend fun delete(marketEntity: MarketEntity) {
        dao.delete(marketEntity)
    }

    override suspend fun insert(marketEntity: MarketEntity) {
        dao.insert(marketEntity)
    }


}