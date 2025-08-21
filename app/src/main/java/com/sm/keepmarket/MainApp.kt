package com.sm.keepmarket

import android.app.Application
import com.sm.keepmarket.di.appDispatchersModule
import com.sm.keepmarket.di.databaseModule
import com.sm.keepmarket.di.datasourceModules
import com.sm.keepmarket.di.repositoryModules
import com.sm.keepmarket.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            AndroidLogger()
            androidContext(this@MainApp)
            modules(viewModelModules, repositoryModules, databaseModule, datasourceModules,
                appDispatchersModule
            )

        }
    }
}