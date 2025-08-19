package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryItemDatasource
import com.sm.keepmarket.data.db.dao.PantryItemDao
import com.sm.keepmarket.data.model.PantryItemEntity
import kotlinx.coroutines.flow.Flow

class PantryItemDatasourceImpl(dao: PantryItemDao): IPantryItemDatasource {
    override suspend fun getAll(): Flow<List<PantryItemEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllByOwner(ownerId: String): Flow<List<PantryItemEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): Flow<PantryItemEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(pantryItemEntity: PantryItemEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(pantryItemEntity: PantryItemEntity) {
        TODO("Not yet implemented")
    }

}