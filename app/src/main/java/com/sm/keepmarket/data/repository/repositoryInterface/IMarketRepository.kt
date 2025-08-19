package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.domain.Market
import kotlinx.coroutines.flow.Flow

interface IMarketRepository {

    suspend fun getAll(): Flow<List<Market>>
    suspend fun getById(id: String): Flow<Market?>
    suspend fun delete(market: Market)
    suspend fun insert(market: Market)
}