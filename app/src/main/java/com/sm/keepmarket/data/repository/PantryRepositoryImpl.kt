package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryDatasource
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.Pantry
import com.sm.keepmarket.util.FeaturedType
import com.sm.keepmarket.util.Mock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.util.UUID

class PantryRepositoryImpl(pantryDatasource: IPantryDatasource): IPantryRepository {

    override suspend fun getAll(): Flow<List<Pantry>> {
        return flow {
            emit(Mock.getPantryList())
        }
    }

    override suspend fun getById(id: String): Flow<Pantry?> {
        return flow {
            emit(Pantry(
                id = UUID.randomUUID().toString(),
                name = "Principal dispensa",
                featuredType = FeaturedType.PANTRY,
                lastUpdate = LocalDateTime.now(),
                items = Mock.getPantryItemList()
            ))
        }
    }

    override suspend fun delete(pantry: Pantry) {

    }

    override suspend fun insert(pantry: Pantry) {

    }
}