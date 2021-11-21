package com.io.pet_newsapp.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val sLogLevel =
    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE


private const val currentUrl = "https://newsapi.org/v2/"

fun createNetworkClient() = retrofitClient(
    okHttpClient()
)

private fun retrofitClient(httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(currentUrl)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


private fun getLogInterceptor() = HttpLoggingInterceptor().apply { level = sLogLevel }

private fun okHttpClient() = OkHttpClient.Builder()
    .addInterceptor(getLogInterceptor()).apply {
        setTimeOutToOkHttpClient(this)
    }
    .addInterceptor(headersInterceptor())
    .build()

fun headersInterceptor() = Interceptor { chain ->
    val url = chain.request().url.newBuilder()
        .addQueryParameter("apiKey", "e0670b0013b245e29427f7f2b9902407").build()
    chain.proceed(chain.request().newBuilder().url(url).build())
}

private fun setTimeOutToOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
    okHttpClientBuilder.apply {
        readTimeout(30L, TimeUnit.SECONDS)
        connectTimeout(30L, TimeUnit.SECONDS)
        writeTimeout(30L, TimeUnit.SECONDS)
    }