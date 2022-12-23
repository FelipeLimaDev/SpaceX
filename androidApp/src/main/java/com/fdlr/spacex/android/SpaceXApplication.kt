package com.fdlr.spacex.android

import android.app.Application
import com.fdlr.spacex.android.di.databaseModule
import com.fdlr.spacex.android.di.networkModule
import com.fdlr.spacex.android.di.useCaseModule
import com.fdlr.spacex.android.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SpaceXApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@SpaceXApplication)
            androidFileProperties()
            modules(
                viewModelModule,
                useCaseModule,
                networkModule,
                databaseModule,
            )
        }
    }
}
