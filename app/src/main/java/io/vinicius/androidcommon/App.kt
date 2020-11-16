package io.vinicius.androidcommon

import android.app.Application
import io.vinicius.androidcommon.di.serviceModule
import io.vinicius.androidcommon.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application()
{
    override fun onCreate()
    {
        super.onCreate()

        // Timber logging
        setupTimber()

        // Dependency injection
        setupKoin()
    }

    // region Private Methods
    private fun setupTimber()
    {
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupKoin()
    {
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(serviceModule, viewModelModule)
        }
    }
    // endregion
}