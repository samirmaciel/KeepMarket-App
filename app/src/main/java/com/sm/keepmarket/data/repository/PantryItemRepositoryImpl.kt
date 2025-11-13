package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryItemDatasource
import com.sm.keepmarket.data.mapper.PantryItemMapper
import com.sm.keepmarket.data.model.PantryItemEntity
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IUserRepository
import com.sm.keepmarket.domain.model.PantryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class PantryItemRepositoryImpl(private val pantryItemDatasource: IPantryItemDatasource, private val userRepository: IUserRepository) :
    IPantryItemRepository {

    override suspend fun getAll(): Flow<List<PantryItem>> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

//            if(userID == null){
//                throw IllegalArgumentException("Not found current User")
//            }
//
//            pantryItemDatasource.getAllByPantryID().collect { pantryItemEntityList ->
//                emit(pantryItemEntityList.map { itemEntity -> PantryItemMapper.toPantryItem(itemEntity) })
//            }
        }
    }

    override suspend fun getAllByOwner(ownerId: String): Flow<List<PantryItem>> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            pantryItemDatasource.getAllByPantryID(ownerId, userID).collect { pantryItemEntityList ->
                emit(pantryItemEntityList.map { itemEntity ->  PantryItemMapper.toPantryItem(itemEntity) })
            }
        }.catch { e -> emit(emptyList()) }
    }

    override suspend fun getById(id: String): Flow<PantryItem?> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            pantryItemDatasource.getById(id, userID).collect { pantryItemEntity ->

                pantryItemEntity?.let {
                    emit(PantryItemMapper.toPantryItem(pantryItemEntity))
                }
            }
        }.catch { e ->  }
    }

    override suspend fun delete(pantryItem: PantryItem) {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        pantryItemDatasource.deleteByID(pantryItem.id, userID)
    }

    override suspend fun insert(pantryItem: PantryItem) {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        pantryItemDatasource.insert(PantryItemMapper.toEntity(pantryItem), userID)
    }
}