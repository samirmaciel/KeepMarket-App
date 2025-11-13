package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryItemDatasource
import com.sm.keepmarket.data.mapper.PantryItemMapper
import com.sm.keepmarket.data.mapper.PantryMapper
import com.sm.keepmarket.data.model.PantryEntity
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IUserRepository
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.Pantry
import com.sm.keepmarket.util.FeaturedType
import com.sm.keepmarket.util.Mock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.util.UUID

class PantryRepositoryImpl(private val pantryDatasource: IPantryDatasource, private val pantryItemDatasource: IPantryItemDatasource, private val userRepository: IUserRepository): IPantryRepository {

    override suspend fun getAll(): Flow<List<Pantry>> {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            pantryDatasource.getAllByUserID(userID).collect { pantryEntityList ->

                val pantryList: MutableList<Pantry> = mutableListOf()

                pantryEntityList.forEach { pantryEntity ->
                    pantryEntity?.let {
                        val pantry = PantryMapper.toPantry(it)

                        pantryItemDatasource.getAllByPantryID(pantry.id, userID).collect { pantryItemEntityList ->
                            pantry.items = pantryItemEntityList.map { item -> PantryItemMapper.toPantryItem(item) }
                        }

                        pantryList.add(pantry)
                    }
                }

                emit(pantryList)
            }
        }
    }

    override suspend fun getById(id: String): Flow<Pantry?> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            pantryDatasource.getById(userID, id).collect { pantryEntity ->
                pantryEntity?.let {
                    val pantry = PantryMapper.toPantry(it)

                    pantryItemDatasource.getAllByPantryID(pantry.id, userID).collect { pantryItemEntityList ->
                        pantry.items = pantryItemEntityList.map { item -> PantryItemMapper.toPantryItem(item) }
                    }

                    emit(pantry)
                }
            }
        }
    }

    override suspend fun delete(pantry: Pantry) {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        pantryDatasource.deleteByID(userID, pantry.id)
    }

    override suspend fun insert(pantry: Pantry) {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        pantryDatasource.insert(userID, PantryMapper.toEntity(pantry))
    }
}