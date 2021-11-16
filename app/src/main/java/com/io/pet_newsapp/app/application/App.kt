package com.io.pet_newsapp

import android.app.Application
import com.io.pet_newsapp.common.data.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    networkModule,
                    useCaseModule
                )
            )
        }

    }
}