package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IHighlightDatasource
import com.sm.keepmarket.data.mapper.HighlightMapper
import com.sm.keepmarket.data.repository.repositoryInterface.IHighlightRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IUserRepository
import com.sm.keepmarket.domain.model.Highlight
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class HighlightRepositoryImpl(private val highlightDatasource : IHighlightDatasource, private val userRepository: IUserRepository): IHighlightRepository {

    override suspend fun getAll(): Flow<List<Highlight>> {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            highlightDatasource.getAllByUserId(userID).collect { highlightEntities ->
                emit(highlightEntities.map { HighlightMapper.toHighlight(it) })
            }
        }.catch { e ->
            emit(emptyList())
        }
    }

    override suspend fun getById(id: String): Flow<Highlight?> {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            highlightDatasource.getById(userID, id).collect { highlightEntity ->

                highlightEntity?.let {
                    emit( HighlightMapper.toHighlight(highlightEntity))
                }?.run {
                    emit(null)
                }
            }
        }.catch { e -> emit(null) }
    }

    override suspend fun delete(highlight: Highlight) {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        highlightDatasource.deleteAllByUserID(userID)
    }

    override suspend fun insert(highlight: Highlight) {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        highlightDatasource.insert(userID, HighlightMapper.toEntity(highlight))
    }
}