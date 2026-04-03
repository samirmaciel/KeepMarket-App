package com.sm.keepmarket.data.repository

import android.util.Log
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemStateDatasource
import com.sm.keepmarket.data.mapper.MarketItemStateMapper
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemStateRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IUserRepository
import com.sm.keepmarket.domain.model.MarketItemState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class MarketItemStateRepositoryImpl(private val marketItemStateDatasource: IMarketItemStateDatasource, private val userRepository: IUserRepository) : IMarketItemStateRepository {

    private val TAG = this.javaClass.name

    override suspend fun insert(marketItemState: MarketItemState) {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        marketItemStateDatasource.insert(MarketItemStateMapper.toEntity(marketItemState), userID)
    }

    override suspend fun delete(marketItemState: MarketItemState) {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found current User")
        }

        marketItemStateDatasource.deleteByID(marketItemState.id, userID)
    }

    override suspend fun getAllByMarketId(marketId: String): Flow<List<MarketItemState>> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

       return flow {

           if(userID == null){
               throw IllegalArgumentException("Not found current User")
           }

           marketItemStateDatasource.getAllByMarketID(marketId, userID).collect{
               emit(it.map {
                   val item = MarketItemStateMapper.toModel(it)
                    item
               })
           }
       }.catch {
           e->
           Log.d(TAG, e.message.toString())
           emit(emptyList()) }
    }

    override suspend fun getById(itemStateId: String): Flow<MarketItemState?> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            marketItemStateDatasource.getByID(itemStateId, userID).collect { itemStateEntity ->

                itemStateEntity?.let {
                    emit(MarketItemStateMapper.toModel(it))
                } ?: run {
                    emit(null)
                }

            }
        }.catch { e -> emit(null) }
    }

    override suspend fun getLastByName(itemStateName: String): Flow<MarketItemState?> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            marketItemStateDatasource.getLastByName(itemStateName, userID).collect { itemStateEntity ->

                itemStateEntity?.let {
                    emit(MarketItemStateMapper.toModel(it))
                } ?: run {
                    emit(null)
                }

            }
        }.catch { e -> emit(null) }
    }

    override suspend fun getLastByProductName(itemStateProductName: String): Flow<MarketItemState?> {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found current User")
            }

            marketItemStateDatasource.getLastByProductName(itemStateProductName, userID).collect { itemStateEntity ->

                itemStateEntity?.let {
                    emit(MarketItemStateMapper.toModel(it))
                } ?: run {
                    emit(null)
                }

            }
        }.catch { e -> emit(null) }
    }
}