package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IHighlightDatasource
import com.sm.keepmarket.data.mapper.HighlightMapper
import com.sm.keepmarket.data.repository.repositoryInterface.IHighlightRepository
import com.sm.keepmarket.domain.model.Highlight
import com.sm.keepmarket.util.Mock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HighlightRepositoryImpl(private val highlightDatasource : IHighlightDatasource): IHighlightRepository {

    override suspend fun getAll(): Flow<List<Highlight>> {
        return flow {
            highlightDatasource.getAll().collect { highlightEntities ->
                emit(highlightEntities.map { HighlightMapper.toHighlight(it) })
            }
        }
    }

    override suspend fun getById(id: String): Flow<Highlight?> {
        return flow {
            highlightDatasource.getById(id).collect { highlightEntity ->

                highlightEntity?.let {
                    emit( HighlightMapper.toHighlight(highlightEntity))
                }?.run {
                    emit(null)
                }
            }
        }
    }

    override suspend fun delete(highlight: Highlight) {
        highlightDatasource.delete(HighlightMapper.toEntity(highlight))
    }

    override suspend fun insert(highlight: Highlight) {
        highlightDatasource.insert(HighlightMapper.toEntity(highlight))
    }
}