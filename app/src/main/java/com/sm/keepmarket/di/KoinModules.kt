package com.sm.keepmarket.di

import androidx.room.Room
import com.sm.keepmarket.data.db.AppDataBase
import org.koin.dsl.module

val viewModelModules = module {

}

val repositoryModules = module {

}

val datasourceModules = module {

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