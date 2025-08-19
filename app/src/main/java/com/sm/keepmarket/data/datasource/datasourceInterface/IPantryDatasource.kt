package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.PantryEntity
import kotlinx.coroutines.flow.Flow

interface IPantryDatasource {

    suspend fun getAll(): Flow<List<PantryEntity>>
    suspend fun getById(id: String): Flow<PantryEntity?>
    suspend fun delete(pantryEntity: PantryEntity)
    suspend fun insert(pantryEntity: PantryEntity)
}