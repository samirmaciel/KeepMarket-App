package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryItemDatasource
import com.sm.keepmarket.data.db.dao.PantryItemDao
import com.sm.keepmarket.data.model.PantryItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PantryItemDatasourceImpl(private val dao: PantryItemDao): IPantryItemDatasource {
    override suspend fun getAll(): Flow<List<PantryItemEntity>> {
        return flow {
            emit(dao.getAll())
        }
    }

    override suspend fun getAllByOwner(ownerId: String): Flow<List<PantryItemEntity>> {
        return flow {
            emit(dao.getAllByOwner(ownerId))
        }
    }

    override suspend fun getById(id: String): Flow<PantryItemEntity?> {
        return flow {
            emit(dao.getById(id))
        }
    }

    override suspend fun delete(pantryItemEntity: PantryItemEntity) {
        dao.delete(pantryItemEntity)
    }

    override suspend fun insert(pantryItemEntity: PantryItemEntity) {
        dao.insert(pantryItemEntity)
    }

}