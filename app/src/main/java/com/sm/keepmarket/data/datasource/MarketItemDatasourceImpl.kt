package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemDatasource
import com.sm.keepmarket.data.db.dao.MarketItemDao
import com.sm.keepmarket.data.model.MarketItemEntity
import kotlinx.coroutines.flow.Flow

class MarketItemDatasourceImpl(private val dao: MarketItemDao): IMarketItemDatasource {
    override suspend fun getAll(): Flow<List<MarketItemEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllByOwner(ownerId: String): Flow<List<MarketItemEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): Flow<MarketItemEntity?> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(marketItemEntity: MarketItemEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(marketItemEntity: MarketItemEntity) {
        TODO("Not yet implemented")
    }

}