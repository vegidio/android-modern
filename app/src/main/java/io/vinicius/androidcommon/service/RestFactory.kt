package io.vinicius.androidcommon.service

import android.content.Context
import com.squareup.moshi.Moshi
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RestFactory(private val context: Context, private val loggingInterceptor: HttpLoggingInterceptor)
{
    private val cacheSize = 10 * 1024 * 1024

    fun <T> create(clazz: Class<T>, time: Long? = null, unit: TimeUnit? = null): T
    {
        // Add the request headers
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(requestHeadersInterceptor())

        // Add the cache support
        if (time != null && unit != null) {
            client.cache(Cache(context.cacheDir, cacheSize.toLong()))
                .addInterceptor(cachePolicyInterceptor(time, unit))
        }

        // Use reflection to get the base URL from the service
        val baseUrl = clazz.getField("baseUrl").get(null) as String

        // Initialize Retrofit with the OkHttp client
        val retrofit = Retrofit.Builder().client(client.build())
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .baseUrl(baseUrl)
            .build()

        return retrofit.create(clazz)
    }

    // region - Interceptors
    private fun requestHeadersInterceptor(): Interceptor
    {
        return Interceptor {
            val request = it.request().newBuilder()
                .header("X-Parse-Application-Id", "parse_demo")
                .header("X-Parse-REST-API-Key", "undefined")
                .build()

            it.proceed(request)
        }
    }

    private fun cachePolicyInterceptor(period: Long, unit: TimeUnit): Interceptor
    {
        val seconds = unit.toSeconds(period)

        return Interceptor {
            val request = it.request().newBuilder()
                .header("Cache-Control", "public, max-stale=$seconds")
                .build()

            it.proceed(request)
        }
    }
    // endregion
}