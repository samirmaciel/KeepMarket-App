package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.domain.Market
import com.sm.keepmarket.domain.Pantry
import kotlinx.coroutines.flow.Flow

interface IFeaturedCardRepository {

    suspend fun getAllMarket(): Flow<List<Market>>
    suspend fun getAllPantry(): Flow<List<Pantry>>
}