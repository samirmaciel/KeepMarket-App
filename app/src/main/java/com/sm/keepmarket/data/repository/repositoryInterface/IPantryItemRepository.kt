package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.data.model.PantryItemEntity
import com.sm.keepmarket.domain.model.PantryItem
import kotlinx.coroutines.flow.Flow

interface IPantryItemRepository {

    suspend fun getAll(): Flow<List<PantryItem>>
    suspend fun getAllByOwner(ownerId: String): Flow<List<PantryItem>>
    suspend fun getById(id: String): Flow<PantryItem?>
    suspend fun delete(pantryItem: PantryItem)
    suspend fun insert(pantryItem: PantryItem)
}