package io.vinicius.androidcommon.service

import io.vinicius.androidcommon.model.Country
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesService
{
    companion object {
        internal const val baseUrl = "https://restcountries.eu/rest/v2/"
    }

    @GET("all")
    fun getCountries(): Flow<List<Country>>

    @GET("alpha/{countryCode}")
    fun getCountryByCode(
        @Path("countryCode") countryCode: String
    ): Flow<Country>
}