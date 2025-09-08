package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.IHighlightDatasource
import com.sm.keepmarket.data.db.dao.HighlightDao
import com.sm.keepmarket.data.model.HighlightEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HighlightDatasourceImpl(private val dao: HighlightDao): IHighlightDatasource {

    override suspend fun getAll(): Flow<List<HighlightEntity>> {
        return flow {
            emit(dao.getAll())
        }
    }

    override suspend fun getById(id: String): Flow<HighlightEntity?> {
        return flow {
            emit(dao.getById(id))
        }
    }

    override suspend fun delete(highlight: HighlightEntity) {
        dao.delete(highlight)
    }

    override suspend fun insert(highlight: HighlightEntity) {
        dao.insert(highlight)
    }
}