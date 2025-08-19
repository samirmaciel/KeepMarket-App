package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.domain.Highlight
import kotlinx.coroutines.flow.Flow

interface IHighlightRepository {

    suspend fun getAll(): Flow<List<Highlight>>
    suspend fun getById(id: String): Flow<List<Highlight>>
    suspend fun delete(highlight: Highlight)
    suspend fun insert(highlight: Highlight)
}