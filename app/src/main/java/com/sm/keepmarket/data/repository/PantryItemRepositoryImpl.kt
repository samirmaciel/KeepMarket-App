package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryItemDatasource
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryItemRepository
import com.sm.keepmarket.domain.model.PantryItem
import kotlinx.coroutines.flow.Flow

class PantryItemRepositoryImpl(pantryItemDatasource: IPantryItemDatasource) :
    IPantryItemRepository {
    override suspend fun getAll(): Flow<List<PantryItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllByOwner(ownerId: String): Flow<List<PantryItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): Flow<PantryItem> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(pantryItem: PantryItem) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(pantryItem: PantryItem) {
        TODO("Not yet implemented")
    }
}