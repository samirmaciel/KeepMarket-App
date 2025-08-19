package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.domain.Market
import com.sm.keepmarket.domain.Pantry
import kotlinx.coroutines.flow.Flow

interface IPantryRepository {

    suspend fun getAll(): Flow<List<Pantry>>
    suspend fun getById(id: String): Flow<Pantry?>
    suspend fun delete(market: Market)
    suspend fun insert(market: Market)
}