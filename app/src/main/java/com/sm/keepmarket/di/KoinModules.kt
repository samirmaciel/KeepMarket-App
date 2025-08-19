package com.sm.keepmarket.di

import androidx.room.Room
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryItemDatasource
import com.sm.keepmarket.data.datasource.MarketDatasourceImpl
import com.sm.keepmarket.data.datasource.MarketItemDatasourceImpl
import com.sm.keepmarket.data.datasource.PantryDatasourceImpl
import com.sm.keepmarket.data.datasource.PantryItemDatasourceImpl
import com.sm.keepmarket.data.db.AppDataBase
import com.sm.keepmarket.data.repository.FeaturedCardRepositoryImpl
import com.sm.keepmarket.data.repository.repositoryInterface.IFeaturedCardRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.data.repository.MarketItemRepositoryImpl
import com.sm.keepmarket.data.repository.MarketRepositoryImpl
import com.sm.keepmarket.data.repository.PantryItemRepositoryImpl
import com.sm.keepmarket.data.repository.PantryRepositoryImpl
import org.koin.dsl.module

val viewModelModules = module {

}

val repositoryModules = module {
    single<IMarketRepository> { MarketRepositoryImpl(get()) }
    single<IPantryRepository> { PantryRepositoryImpl(get()) }
    single<IMarketItemRepository>{ MarketItemRepositoryImpl(get()) }
    single<IPantryItemRepository> { PantryItemRepositoryImpl(get()) }
    single<IFeaturedCardRepository> { FeaturedCardRepositoryImpl(get(), get()) }
}

val datasourceModules = module {
    single<IMarketDatasource> { MarketDatasourceImpl(get()) }
    single<IPantryDatasource>{ PantryDatasourceImpl(get()) }
    single<IMarketItemDatasource> { MarketItemDatasourceImpl(get()) }
    single<IPantryItemDatasource> { PantryItemDatasourceImpl(get()) }
}

val appDispatchersModule = module {

}

val databaseModule = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDataBase::class.java,
            "keepMarket_db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    single { get<AppDataBase>().MarketDao() }
    single { get<AppDataBase>().PantryDao() }
    single { get<AppDataBase>().MarketItemDao() }
    single { get<AppDataBase>().PantryItemDao() }
    single { get<AppDataBase>().NotificationDao() }
    single { get<AppDataBase>().HighlightDao() }

}