package com.io.pet_newsapp.data.news.remote

import com.io.pet_newsapp.data.news.entity.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String = "tesla",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String = "e0670b0013b245e29427f7f2b9902407"
    ): NewsModel
}