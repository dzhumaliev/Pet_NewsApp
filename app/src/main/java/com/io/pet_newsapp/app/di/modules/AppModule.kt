package com.io.pet_newsapp.app.di.modules

import com.io.pet_newsapp.data.network.createNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun retrofit(): Retrofit = createNetworkClient()
}