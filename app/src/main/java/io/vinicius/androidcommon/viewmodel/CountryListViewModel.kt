package io.vinicius.androidcommon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.vinicius.androidcommon.constant.NetworkState
import io.vinicius.androidcommon.model.Country
import io.vinicius.androidcommon.service.CountriesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CountryListViewModel : ViewModel(), KoinComponent
{
    private val service by inject<CountriesService>()

    val state = MutableStateFlow(NetworkState.IDLE)
    val countries = MutableStateFlow(emptyList<Country>())

    fun getCountries() = viewModelScope.launch {
        service.getCountries()
            .onStart { state.value = NetworkState.LOADING }
            .catch { state.value = NetworkState.ERROR }
            .map { it.sortedBy(Country::name) }
            .flowOn(Dispatchers.IO)
            .collect {
                countries.value = it
                state.value = NetworkState.IDLE
            }
    }
}