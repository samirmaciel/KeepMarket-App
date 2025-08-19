package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.PantryItemEntity
import kotlinx.coroutines.flow.Flow

interface IPantryItemDatasource {

    suspend fun getAll(): Flow<List<PantryItemEntity>>
    suspend fun getAllByOwner(ownerId: String): Flow<List<PantryItemEntity>>
    suspend fun getById(id: String): Flow<PantryItemEntity>
    suspend fun delete(pantryItemEntity: PantryItemEntity)
    suspend fun insert(pantryItemEntity: PantryItemEntity)
}