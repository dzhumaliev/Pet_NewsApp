package com.io.pet_newsapp.data.news.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.io.pet_newsapp.data.news.datasource.NewsPagingSource
import com.io.pet_newsapp.data.news.remote.Api
import com.io.pet_newsapp.domain.news.entity.Articles
import com.io.pet_newsapp.domain.news.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val api: Api
) : NewsRepository {

    override fun getNews(q: String): Flow<PagingData<Articles>> =
        Pager(config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 3
        ),
            pagingSourceFactory = { NewsPagingSource(api, q) }
        ).flow
}