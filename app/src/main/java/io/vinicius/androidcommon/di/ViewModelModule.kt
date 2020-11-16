package io.vinicius.androidcommon.di

import io.vinicius.androidcommon.viewmodel.CountryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    // CountryListViewModel
    viewModel {
        CountryListViewModel()
    }
}