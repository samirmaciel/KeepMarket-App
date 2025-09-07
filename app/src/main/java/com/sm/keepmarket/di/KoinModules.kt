package com.sm.keepmarket.di

import androidx.room.Room
import com.sm.keepmarket.data.datasource.HighlightDatasourceImpl
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryItemDatasource
import com.sm.keepmarket.data.datasource.MarketDatasourceImpl
import com.sm.keepmarket.data.datasource.MarketItemDatasourceImpl
import com.sm.keepmarket.data.datasource.MarketItemStateDatasourceImpl
import com.sm.keepmarket.data.datasource.NotificationDatasourceImpl
import com.sm.keepmarket.data.datasource.PantryDatasourceImpl
import com.sm.keepmarket.data.datasource.PantryItemDatasourceImpl
import com.sm.keepmarket.data.datasource.datasourceInterface.IHighlightDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemStateDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.INotificationDatasource
import com.sm.keepmarket.data.db.AppDataBase
import com.sm.keepmarket.data.repository.HighlightRepositoryImpl
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.data.repository.MarketItemRepositoryImpl
import com.sm.keepmarket.data.repository.MarketRepositoryImpl
import com.sm.keepmarket.data.repository.NotificationRepositoryImpl
import com.sm.keepmarket.data.repository.PantryItemRepositoryImpl
import com.sm.keepmarket.data.repository.PantryRepositoryImpl
import com.sm.keepmarket.data.repository.repositoryInterface.IHighlightRepository
import com.sm.keepmarket.data.repository.repositoryInterface.INotificationRepository
import com.sm.keepmarket.presentation.home.HomeViewModel
import com.sm.keepmarket.presentation.marketList.MarketListSelectionViewModel
import com.sm.keepmarket.presentation.marketList.MarketListViewModel
import com.sm.keepmarket.presentation.notifications.NotificationsViewModel
import com.sm.keepmarket.presentation.pantryList.PantryListSelectionViewModel
import com.sm.keepmarket.presentation.pantryList.PantryViewModel
import com.sm.keepmarket.presentation.search.SearchViewModel
import com.sm.keepmarket.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModules = module {
    viewModel{ HomeViewModel(get(), get(), get()) }
    viewModel{ PantryViewModel(get(), get()) }
    viewModel{ NotificationsViewModel(get()) }
    viewModel{ SearchViewModel(get()) }
    viewModel{ MarketListViewModel(get(), get()) }
    viewModel{ SplashViewModel(get(), get()) }
    viewModel{ MarketListSelectionViewModel(get()) }
    viewModel{ PantryListSelectionViewModel(get()) }
}

val repositoryModules = module {
    single<IMarketRepository> { MarketRepositoryImpl(get(), get()) }
    single<IPantryRepository> { PantryRepositoryImpl(get(), get()) }
    single<IMarketItemRepository>{ MarketItemRepositoryImpl(get()) }
    single<IPantryItemRepository> { PantryItemRepositoryImpl(get()) }
    single<IHighlightRepository> { HighlightRepositoryImpl(get()) }
    single<INotificationRepository> { NotificationRepositoryImpl(get()) }
}

val datasourceModules = module {
    single<IMarketDatasource> { MarketDatasourceImpl(get()) }
    single<IPantryDatasource>{ PantryDatasourceImpl(get()) }
    single<IMarketItemDatasource> { MarketItemDatasourceImpl(get()) }
    single<IPantryItemDatasource> { PantryItemDatasourceImpl(get()) }
    single<IHighlightDatasource> { HighlightDatasourceImpl(get()) }
    single<INotificationDatasource> { NotificationDatasourceImpl(get()) }
    single<IMarketItemStateDatasource> { MarketItemStateDatasourceImpl(get()) }
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
    single { get<AppDataBase>().MarketItemStateDao() }

}