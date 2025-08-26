package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryDatasource
import com.sm.keepmarket.data.db.dao.PantryDao
import com.sm.keepmarket.data.model.PantryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PantryDatasourceImpl(private val dao: PantryDao): IPantryDatasource {

    override suspend fun getAll(): Flow<List<PantryEntity>> {
        return flow {
            emit(dao.getAll())
        }
    }

    override suspend fun getById(id: String): Flow<PantryEntity?> {
        return flow {
            emit(dao.getById(id))
        }
    }

    override suspend fun delete(pantryEntity: PantryEntity) {
        dao.delete(pantryEntity)
    }

    override suspend fun insert(pantryEntity: PantryEntity) {
        dao.insert(pantryEntity)
    }

}