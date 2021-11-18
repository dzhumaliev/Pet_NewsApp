package com.io.pet_newsapp.app.di.modules

import com.io.pet_newsapp.data.network.createNetworkClient
import com.io.pet_newsapp.data.news.remote.Api
import org.koin.dsl.module
import retrofit2.Retrofit

private val retrofit: Retrofit = createNetworkClient()

private val API: Api = retrofit.create(Api::class.java)

val networkModule = module {
    single { API }
}