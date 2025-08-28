package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IHighlightDatasource
import com.sm.keepmarket.data.repository.repositoryInterface.IHighlightRepository
import com.sm.keepmarket.domain.model.Highlight
import com.sm.keepmarket.util.Mock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HighlightRepositoryImpl(highlightDatasource : IHighlightDatasource): IHighlightRepository {
    override suspend fun getAll(): Flow<List<Highlight>> {
        return flow {

        }
    }

    override suspend fun getById(id: String): Flow<List<Highlight>> {
        return flow {

        }
    }

    override suspend fun delete(highlight: Highlight) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(highlight: Highlight) {
        TODO("Not yet implemented")
    }
}