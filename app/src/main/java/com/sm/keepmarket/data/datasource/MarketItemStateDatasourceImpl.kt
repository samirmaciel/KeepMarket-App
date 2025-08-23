package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemStateDatasource
import com.sm.keepmarket.data.db.dao.MarketItemStateDao
import com.sm.keepmarket.data.model.MarketItemStateEntity
import kotlinx.coroutines.flow.Flow

class MarketItemStateDatasourceImpl(dao: MarketItemStateDao) : IMarketItemStateDatasource {

    override fun getAll(): Flow<List<MarketItemStateEntity>> {
        TODO("Not yet implemented")
    }

    override fun insert(item: MarketItemStateEntity) {
        TODO("Not yet implemented")
    }

    override fun delete(item: MarketItemStateEntity) {
        TODO("Not yet implemented")
    }

    override fun getByID(id: String): Flow<MarketItemStateEntity?> {
        TODO("Not yet implemented")
    }

    override fun getByOwnerID(ownerID: String): Flow<List<MarketItemStateEntity>> {
        TODO("Not yet implemented")
    }
}