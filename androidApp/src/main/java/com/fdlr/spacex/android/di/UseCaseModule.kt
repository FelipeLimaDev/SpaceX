package com.fdlr.spacex.android.di

import com.fdlr.spacex.useCases.HomeNewsUseCase
import com.fdlr.spacex.useCases.UseCases
import org.koin.dsl.module

val useCaseModule = module {
    factory { UseCases(get()) }
    factory { HomeNewsUseCase(get()) }
}