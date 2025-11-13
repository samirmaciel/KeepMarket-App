package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.PantryItemEntity
import kotlinx.coroutines.flow.Flow

interface IPantryItemDatasource {

    suspend fun getAllByPantryID(pantryID: String, userID: String): Flow<List<PantryItemEntity>>
    suspend fun getById(pantryItemID: String, userID: String): Flow<PantryItemEntity?>
    suspend fun deleteByID(pantryItemID: String, userID: String)
    suspend fun deleteAllByPantryID(pantryID: String, userID: String)
    suspend fun insert(pantryItemEntity: PantryItemEntity, userID: String)
}