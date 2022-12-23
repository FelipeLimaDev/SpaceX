package com.fdlr.spacex.android.di

import com.fdlr.spacex.datasource.network.KtorClientFactory
import com.fdlr.spacex.datasource.network.services.SpaceXService
import com.fdlr.spacex.datasource.network.services.SpaceXServiceImpl
import io.ktor.client.*
import org.koin.dsl.module

val networkModule = module {
    single {
        provideHttpClient()
    }

    single<SpaceXService> {
        SpaceXServiceImpl(
            httpClient = get(),
            baseUrl = SpaceXServiceImpl.BASE_URL
        )
    }
}

fun provideHttpClient(): HttpClient{
    return KtorClientFactory().build()
}

