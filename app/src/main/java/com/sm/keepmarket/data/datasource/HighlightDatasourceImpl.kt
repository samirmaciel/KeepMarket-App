package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.IHighlightDatasource
import com.sm.keepmarket.data.db.dao.HighlightDao
import com.sm.keepmarket.data.model.HighlightEntity
import kotlinx.coroutines.flow.Flow

class HighlightDatasourceImpl(dao: HighlightDao): IHighlightDatasource {

    override suspend fun getAll(): Flow<List<HighlightEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): Flow<List<HighlightEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(highlight: HighlightEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(highlight: HighlightEntity) {
        TODO("Not yet implemented")
    }
}