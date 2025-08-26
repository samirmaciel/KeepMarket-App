package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryItemDatasource
import com.sm.keepmarket.data.mapper.PantryItemMapper
import com.sm.keepmarket.data.mapper.PantryMapper
import com.sm.keepmarket.data.model.PantryEntity
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.domain.model.Market
import com.sm.keepmarket.domain.model.Pantry
import com.sm.keepmarket.util.FeaturedType
import com.sm.keepmarket.util.Mock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.util.UUID

class PantryRepositoryImpl(private val pantryDatasource: IPantryDatasource, private val pantryItemDatasource: IPantryItemDatasource): IPantryRepository {

    override suspend fun getAll(): Flow<List<Pantry>> {
        return flow {
            pantryDatasource.getAll().collect { pantryEntityList ->

                val pantryList: MutableList<Pantry> = mutableListOf()

                pantryEntityList.forEach { pantryEntity ->
                    pantryEntity?.let {
                        val pantry = PantryMapper.toPantry(it)

                        pantryItemDatasource.getAllByOwner(pantry.id).collect { pantryItemEntityList ->
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
        return flow {
            pantryDatasource.getById(id).collect { pantryEntity ->
                pantryEntity?.let {
                    val pantry = PantryMapper.toPantry(it)

                    pantryItemDatasource.getAllByOwner(pantry.id).collect { pantryItemEntityList ->
                        pantry.items = pantryItemEntityList.map { item -> PantryItemMapper.toPantryItem(item) }
                    }

                    emit(pantry)
                }
            }
        }
    }

    override suspend fun delete(pantry: Pantry) {
        pantryDatasource.delete(PantryMapper.toEntity(pantry))
    }

    override suspend fun insert(pantry: Pantry) {
        pantryDatasource.insert(PantryMapper.toEntity(pantry))
    }
}