package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemDatasource
import com.sm.keepmarket.data.repository.repositoryInterface.IFeaturedCardRepository
import com.sm.keepmarket.domain.Market
import com.sm.keepmarket.domain.Pantry
import kotlinx.coroutines.flow.Flow

class FeaturedCardRepositoryImpl(marketDatasource: IMarketDatasource, pantryDatasource: IMarketItemDatasource) :
    IFeaturedCardRepository {

    override suspend fun getAllMarket(): Flow<List<Market>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPantry(): Flow<List<Pantry>> {
        TODO("Not yet implemented")
    }
}