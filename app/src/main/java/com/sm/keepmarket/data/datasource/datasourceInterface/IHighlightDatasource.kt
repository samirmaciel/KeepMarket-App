package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.HighlightEntity
import kotlinx.coroutines.flow.Flow

interface IHighlightDatasource {
    suspend fun getAll(): Flow<List<HighlightEntity>>
    suspend fun getById(id: String): Flow<List<HighlightEntity>>
    suspend fun delete(highlight: HighlightEntity)
    suspend fun insert(highlight: HighlightEntity)
}