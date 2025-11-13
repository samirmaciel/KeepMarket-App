package com.sm.keepmarket.data.repository

import android.util.Log
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemDatasource
import com.sm.keepmarket.data.mapper.MarketItemMapper
import com.sm.keepmarket.data.mapper.MarketMapper
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IUserRepository
import com.sm.keepmarket.domain.model.Market
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class MarketRepositoryImpl(private val marketDatasource: IMarketDatasource, private val marketItemDatasource: IMarketItemDatasource, private val userRepository: IUserRepository) : IMarketRepository {

    override suspend fun getAll(): Flow<List<Market>> {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found userID")
            }

            marketDatasource.getAllByUserID(userID).collect { marketEntityList ->

                val marketList = marketEntityList.map { MarketMapper.toMarket(it) }

                marketList.forEach { market ->
                    marketItemDatasource.getAllByMarketID(market.id, userID).collect { marketItemList ->
                        market.items = marketItemList.map { MarketItemMapper.toMarketItem(it) }
                    }
                }

                emit(marketList)
            }
        }
    }

    override suspend fun getById(id: String): Flow<Market?> {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        return flow {

            if(userID == null){
                throw IllegalArgumentException("Not found userID")
            }

            marketDatasource.getById(userID, id).collect { marketEntity ->

                marketEntity?.let {
                    val market = MarketMapper.toMarket(marketEntity)

                    marketItemDatasource.getAllByMarketID(market.id,userID).collect { marketItemEntityList ->
                        market.items = marketItemEntityList.map { MarketItemMapper.toMarketItem(it) }
                    }

                    emit(market)
                } ?: run {
                    emit(null)
                }
            }
        }
    }

    override suspend fun delete(market: Market) {
        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found userID")
        }

        marketDatasource.deleteByID(userID, market.id)
    }

    override suspend fun insert(market: Market) {

        val userID = userRepository.getCurrentUser().firstOrNull()?.uuid

        if(userID == null){
            throw IllegalArgumentException("Not found userID")
        }

        marketDatasource.insert(userID, MarketMapper.toEntity(market))
    }

}