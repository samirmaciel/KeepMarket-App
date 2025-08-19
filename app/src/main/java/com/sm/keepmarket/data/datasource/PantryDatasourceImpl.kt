package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryDatasource
import com.sm.keepmarket.data.db.dao.PantryDao
import com.sm.keepmarket.data.model.PantryEntity
import kotlinx.coroutines.flow.Flow

class PantryDatasourceImpl(dao: PantryDao): IPantryDatasource {

    override suspend fun getAll(): Flow<List<PantryEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: String): Flow<PantryEntity?> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(pantryEntity: PantryEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insert(pantryEntity: PantryEntity) {
        TODO("Not yet implemented")
    }

}