package com.io.pet_newsapp.app.application

import android.app.Application
import com.io.pet_newsapp.app.di.modules.networkModule
import com.io.pet_newsapp.app.di.modules.repositoryModule
import com.io.pet_newsapp.app.di.modules.useCaseModule
import com.io.pet_newsapp.app.di.modules.viewModelModule
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