package com.fdlr.spacex.android.di

import com.fdlr.spacex.android.presentation.details.DetailsViewModel
import com.fdlr.spacex.android.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule  = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}