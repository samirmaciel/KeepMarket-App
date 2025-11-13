package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.HighlightEntity
import kotlinx.coroutines.flow.Flow

interface IHighlightDatasource {
    suspend fun getAllByUserId(userID: String): Flow<List<HighlightEntity>>
    suspend fun getById(userID: String, highlightID: String): Flow<HighlightEntity?>
    suspend fun deleteAllByUserID(userID: String)
    suspend fun deleteByID(userID: String, highlightID: String)
    suspend fun insert(userID: String, highlightEntity: HighlightEntity)
}