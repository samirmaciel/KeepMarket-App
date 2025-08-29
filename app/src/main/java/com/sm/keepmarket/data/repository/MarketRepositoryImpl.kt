package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemDatasource
import com.sm.keepmarket.data.mapper.MarketItemMapper
import com.sm.keepmarket.data.mapper.MarketMapper
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.domain.model.Market
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MarketRepositoryImpl(private val marketDatasource: IMarketDatasource, private val marketItemDatasource: IMarketItemDatasource) : IMarketRepository {

    override suspend fun getAll(): Flow<List<Market>> {
        return flow {
            marketDatasource.getAll().collect { marketEntityList ->

                val marketList = marketEntityList.map { MarketMapper.toMarket(it) }

                marketList.forEach { market ->
                    marketItemDatasource.getAllByOwner(market.id).collect { marketItemList ->
                        market.items = marketItemList.map { MarketItemMapper.toMarketItem(it) }
                    }
                }

                emit(marketList)
            }
        }
    }

    override suspend fun getById(id: String): Flow<Market?> {
        return flow {
            marketDatasource.getById(id).collect { marketEntity ->

                marketEntity?.let {
                    val market = MarketMapper.toMarket(marketEntity)

                    marketItemDatasource.getAllByOwner(market.id).collect { marketItemEntityList ->
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
        marketDatasource.delete(MarketMapper.toEntity(market))
    }

    override suspend fun insert(market: Market) {
        marketDatasource.insert(MarketMapper.toEntity(market))
    }

}