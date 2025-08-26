package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryItemDatasource
import com.sm.keepmarket.data.mapper.PantryItemMapper
import com.sm.keepmarket.data.model.PantryItemEntity
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryItemRepository
import com.sm.keepmarket.domain.model.PantryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PantryItemRepositoryImpl(private val pantryItemDatasource: IPantryItemDatasource) :
    IPantryItemRepository {
    override suspend fun getAll(): Flow<List<PantryItem>> {
        return flow {
            pantryItemDatasource.getAll().collect { pantryItemEntityList ->
                emit(pantryItemEntityList.map { itemEntity -> PantryItemMapper.toPantryItem(itemEntity) })
            }
        }
    }

    override suspend fun getAllByOwner(ownerId: String): Flow<List<PantryItem>> {
        return flow {
            pantryItemDatasource.getAllByOwner(ownerId).collect { pantryItemEntityList ->
                emit(pantryItemEntityList.map { itemEntity ->  PantryItemMapper.toPantryItem(itemEntity) })
            }
        }
    }

    override suspend fun getById(id: String): Flow<PantryItem?> {
        return flow {
            pantryItemDatasource.getById(id).collect { pantryItemEntity ->

                pantryItemEntity?.let {
                    emit(PantryItemMapper.toPantryItem(pantryItemEntity))
                }
            }
        }
    }

    override suspend fun delete(pantryItem: PantryItem) {
        pantryItemDatasource.delete(PantryItemMapper.toEntity(pantryItem))
    }

    override suspend fun insert(pantryItem: PantryItem) {
        pantryItemDatasource.insert(PantryItemMapper.toEntity(pantryItem))
    }
}