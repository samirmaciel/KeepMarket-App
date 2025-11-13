package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.PantryEntity
import kotlinx.coroutines.flow.Flow

interface IPantryDatasource {

    suspend fun getAllByUserID(userID: String): Flow<List<PantryEntity>>
    suspend fun getById(userID: String, pantryID: String): Flow<PantryEntity?>
    suspend fun deleteByID(userID: String, pantryID: String)
    suspend fun deleteAllByUserID(userID: String)
    suspend fun insert(userID: String, pantryEntity: PantryEntity)
}