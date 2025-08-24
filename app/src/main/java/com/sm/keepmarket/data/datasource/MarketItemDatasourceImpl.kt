package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemDatasource
import com.sm.keepmarket.data.db.dao.MarketItemDao
import com.sm.keepmarket.data.model.MarketItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MarketItemDatasourceImpl(private val dao: MarketItemDao): IMarketItemDatasource {
    override suspend fun getAll(): Flow<List<MarketItemEntity>> {
        return flow {
            emit(dao.getAll())
        }
    }

    override suspend fun getAllByOwner(ownerId: String): Flow<List<MarketItemEntity>> {
        return flow {
            emit(dao.getAllByOwner(ownerId))
        }
    }

    override suspend fun getById(id: String): Flow<MarketItemEntity?> {
        return flow {
            emit(dao.getById(id))
        }
    }

    override suspend fun delete(marketItemEntity: MarketItemEntity) {
        dao.delete(marketItemEntity)
    }

    override suspend fun insert(marketItemEntity: MarketItemEntity) {
        dao.insert(marketItemEntity)
    }

}