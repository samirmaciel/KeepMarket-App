package com.sm.keepmarket.di

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.sm.keepmarket.data.datasource.firebase.LoginFirebaseDatasourceImpl
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IPantryItemDatasource
import com.sm.keepmarket.data.datasource.firebase.UserFirebaseDatasourceImpl
import com.sm.keepmarket.data.datasource.datasourceInterface.IHighlightDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.ILoginDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IMarketItemStateDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.INotificationDatasource
import com.sm.keepmarket.data.datasource.datasourceInterface.IUserDatasource
import com.sm.keepmarket.data.datasource.firebase.HighlightFirebaseDatasourceImpl
import com.sm.keepmarket.data.datasource.firebase.MarketFirebaseDatasourceImpl
import com.sm.keepmarket.data.datasource.firebase.MarketItemFirebaseDatasourceImpl
import com.sm.keepmarket.data.datasource.firebase.MarketItemStateFirebaseDatasourceImpl
import com.sm.keepmarket.data.datasource.firebase.NotificationsFirebaseDatasourceImpl
import com.sm.keepmarket.data.datasource.firebase.PantryFirebaseDatasourceImpl
import com.sm.keepmarket.data.datasource.firebase.PantryItemFirebaseDatasourceImpl
import com.sm.keepmarket.data.repository.HighlightRepositoryImpl
import com.sm.keepmarket.data.repository.LoginRepositoryImpl
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryItemRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IPantryRepository
import com.sm.keepmarket.data.repository.MarketItemRepositoryImpl
import com.sm.keepmarket.data.repository.MarketItemStateRepositoryImpl
import com.sm.keepmarket.data.repository.MarketRepositoryImpl
import com.sm.keepmarket.data.repository.NotificationRepositoryImpl
import com.sm.keepmarket.data.repository.PantryItemRepositoryImpl
import com.sm.keepmarket.data.repository.PantryRepositoryImpl
import com.sm.keepmarket.data.repository.UserRepositoryImpl
import com.sm.keepmarket.data.repository.repositoryInterface.IHighlightRepository
import com.sm.keepmarket.data.repository.repositoryInterface.ILoginRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IMarketItemStateRepository
import com.sm.keepmarket.data.repository.repositoryInterface.INotificationRepository
import com.sm.keepmarket.data.repository.repositoryInterface.IUserRepository
import com.sm.keepmarket.presentation.home.HomeViewModel
import com.sm.keepmarket.presentation.login.LoginViewModel
import com.sm.keepmarket.presentation.marketList.MarketListSelectionViewModel
import com.sm.keepmarket.presentation.marketList.MarketListViewModel
import com.sm.keepmarket.presentation.notifications.NotificationsViewModel
import com.sm.keepmarket.presentation.pantryList.PantryListSelectionViewModel
import com.sm.keepmarket.presentation.pantryList.PantryViewModel
import com.sm.keepmarket.presentation.register.RegisterViewModel
import com.sm.keepmarket.presentation.search.SearchViewModel
import com.sm.keepmarket.presentation.splash.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel{ HomeViewModel(get(), get(), get(), get()) }
    viewModel{ PantryViewModel(get(), get()) }
    viewModel{ NotificationsViewModel(get()) }
    viewModel{ SearchViewModel(get()) }
    viewModel{ MarketListViewModel(get(), get(), get(), get()) }
    viewModel{ SplashViewModel(get()) }
    viewModel{ MarketListSelectionViewModel(get()) }
    viewModel{ PantryListSelectionViewModel(get()) }
    viewModel{ LoginViewModel(get()) }
    viewModel{ RegisterViewModel(get()) }
}

val firebase = module {
    single { Firebase.auth }
    single { Firebase.firestore }
}

val repositoryModules = module {
    single<IMarketRepository> { MarketRepositoryImpl(get(), get(), get()) }
    single<IPantryRepository> { PantryRepositoryImpl(get(), get(), get()) }
    single<IMarketItemRepository>{ MarketItemRepositoryImpl(get(), get()) }
    single<IPantryItemRepository> { PantryItemRepositoryImpl(get(), get()) }
    single<IHighlightRepository> { HighlightRepositoryImpl(get(), get()) }
    single<INotificationRepository> { NotificationRepositoryImpl(get(), get()) }
    single<IMarketItemStateRepository> { MarketItemStateRepositoryImpl(get(), get()) }
    single<ILoginRepository> { LoginRepositoryImpl(get()) }
    single<IUserRepository> { UserRepositoryImpl(get()) }
}

val datasourceModules = module {
    single<IMarketDatasource> { MarketFirebaseDatasourceImpl(get()) }
    single<IPantryDatasource>{ PantryFirebaseDatasourceImpl(get()) }
    single<IMarketItemDatasource> { MarketItemFirebaseDatasourceImpl(get()) }
    single<IPantryItemDatasource> { PantryItemFirebaseDatasourceImpl(get()) }
    single<IHighlightDatasource> { HighlightFirebaseDatasourceImpl(get()) }
    single<INotificationDatasource> { NotificationsFirebaseDatasourceImpl(get()) }
    single<IMarketItemStateDatasource> { MarketItemStateFirebaseDatasourceImpl(get()) }
    single<ILoginDatasource> { LoginFirebaseDatasourceImpl(get(), get()) }
    single<IUserDatasource> { UserFirebaseDatasourceImpl(get(), get()) }
}

val appDispatchersModule = module {

}