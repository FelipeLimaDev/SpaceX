package com.fdlr.spacex.android.di

import com.fdlr.spacex.datasource.cache.DriverFactory
import com.fdlr.spacex.datasource.cache.SpaceCache
import com.fdlr.spacex.datasource.cache.SpaceCacheImpl
import com.fdlr.spacex.datasource.cache.SpaceDatabase
import org.koin.dsl.module

val databaseModule = module {

    single { DriverFactory(get()).createDriver() } // SqlDriver
    single { SpaceDatabase(get()) }
    single<SpaceCache> { SpaceCacheImpl(get()) }

}