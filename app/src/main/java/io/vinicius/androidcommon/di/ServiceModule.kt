package io.vinicius.androidcommon.di

import com.squareup.moshi.Moshi
import io.vinicius.androidcommon.service.CountriesService
import io.vinicius.sak.network.FlowCallAdapterFactory
import io.vinicius.sak.network.LogHandler
import io.vinicius.sak.network.RestFactory
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Logger
import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

val serviceModule = module {
    // RestFactory
    single {
        RestFactory(get()).apply {
            converter = MoshiConverterFactory.create(Moshi.Builder().build())
            callAdapter = FlowCallAdapterFactory()
            logHandler = LogHandler(Logger(Timber::d), Level.BASIC)
        }
    }

    // CountriesService
    single {
        val restFactory: RestFactory = get()
        restFactory.create(CountriesService::class, CountriesService.BASE_URL)
    }
}