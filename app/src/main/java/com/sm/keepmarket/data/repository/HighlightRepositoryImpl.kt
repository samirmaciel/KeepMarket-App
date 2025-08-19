package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.repository.repositoryInterface.IHighlightRepository
import com.sm.keepmarket.domain.Highlight
import kotlinx.coroutines.flow.Flow

class HighlightRepositoryImpl: IHighlightRepository {
    override suspend fun getAll(): Flow<List<Highlight>> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): Flow<List<Highlight>> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(highlight: Highlight) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(highlight: Highlight) {
        TODO("Not yet implemented")
    }
}