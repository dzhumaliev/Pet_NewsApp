package com.io.pet_newsapp.domain.news.repository

import androidx.paging.PagingData
import com.io.pet_newsapp.domain.news.entity.Articles
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(q: String): Flow<PagingData<Articles>>

}