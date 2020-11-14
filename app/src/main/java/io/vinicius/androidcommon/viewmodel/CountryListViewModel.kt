package io.vinicius.androidcommon.viewmodel

import androidx.lifecycle.ViewModel
import io.vinicius.androidcommon.constant.NetworkState
import io.vinicius.androidcommon.service.CountriesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CountryListViewModel : ViewModel(), KoinComponent
{
    private val service: CountriesService by inject()

    val state = MutableStateFlow(NetworkState.IDLE)

    fun getCountries() = flow {
        val countries = service.getCountries()
        emit(countries)
    }.flowOn(Dispatchers.IO)
}