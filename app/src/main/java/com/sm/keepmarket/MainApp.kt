package com.sm.keepmarket

import android.app.Application
import com.google.firebase.FirebaseApp
import com.sm.keepmarket.di.appDispatchersModule
import com.sm.keepmarket.di.datasourceModules
import com.sm.keepmarket.di.firebase
import com.sm.keepmarket.di.repositoryModules
import com.sm.keepmarket.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        startKoin {
            AndroidLogger()
            androidContext(this@MainApp)
            modules(viewModelModules, repositoryModules, datasourceModules,
                appDispatchersModule, firebase
            )

        }
    }
}