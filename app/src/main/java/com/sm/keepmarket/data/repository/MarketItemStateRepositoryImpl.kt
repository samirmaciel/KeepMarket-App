package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemStateDatasource
import com.sm.keepmarket.data.mapper.MarketItemStateMapper
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemStateRepository
import com.sm.keepmarket.domain.model.MarketItemState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MarketItemStateRepositoryImpl(private val marketItemStateDatasource: IMarketItemStateDatasource) : IMarketItemStateRepository {

    override suspend fun insert(marketItemState: MarketItemState) {
        marketItemStateDatasource.insert(MarketItemStateMapper.toEntity(marketItemState))
    }

    override suspend fun delete(marketItemState: MarketItemState) {
        marketItemStateDatasource.delete(MarketItemStateMapper.toEntity(marketItemState))
    }

    override suspend fun getAllByMarketId(marketId: String): Flow<List<MarketItemState>> {
       return flow {
           marketItemStateDatasource.getAllByMarketID(marketId).collect{
               emit(it.map { MarketItemStateMapper.toModel(it) })
           }
       }
    }

    override suspend fun getById(itemStateId: String): Flow<MarketItemState?> {
        return flow {
            marketItemStateDatasource.getByID(itemStateId).collect { itemStateEntity ->

                itemStateEntity?.let {
                    emit(MarketItemStateMapper.toModel(it))
                } ?: run {
                    emit(null)
                }

            }
        }
    }

    override suspend fun getLastByName(itemStateName: String): Flow<MarketItemState?> {
        return flow {
            marketItemStateDatasource.getLastByName(itemStateName).collect { itemStateEntity ->

                itemStateEntity?.let {
                    emit(MarketItemStateMapper.toModel(it))
                } ?: run {
                    emit(null)
                }

            }
        }
    }
}