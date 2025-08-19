package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketDatasource
import com.sm.keepmarket.data.db.dao.MarketDao
import com.sm.keepmarket.data.model.MarketEntity
import kotlinx.coroutines.flow.Flow

class MarketDatasourceImpl(private val dao: MarketDao): IMarketDatasource {

    override suspend fun getAll(): Flow<List<MarketEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): Flow<MarketEntity?> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(marketEntity: MarketEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(marketEntity: MarketEntity) {
        TODO("Not yet implemented")
    }


}