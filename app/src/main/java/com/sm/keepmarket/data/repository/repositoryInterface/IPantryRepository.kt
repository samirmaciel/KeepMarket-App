package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.Pantry
import kotlinx.coroutines.flow.Flow

interface IPantryRepository {

    suspend fun getAll(): Flow<List<Pantry>>
    suspend fun getById(id: String): Flow<Pantry?>
    suspend fun delete(pantry: Pantry)
    suspend fun insert(pantry: Pantry)
}