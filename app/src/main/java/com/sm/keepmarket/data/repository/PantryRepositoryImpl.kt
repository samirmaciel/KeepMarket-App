package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryDatasource
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.domain.Market
import com.sm.keepmarket.domain.Pantry
import kotlinx.coroutines.flow.Flow

class PantryRepositoryImpl(pantryDatasource: IPantryDatasource): IPantryRepository {

    override suspend fun getAll(): Flow<List<Pantry>> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): Flow<Pantry?> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(market: Market) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(market: Market) {
        TODO("Not yet implemented")
    }
}