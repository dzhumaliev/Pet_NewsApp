package com.io.pet_newsapp.data.news.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.io.pet_newsapp.data.news.datasource.NewsPagingSource
import com.io.pet_newsapp.domain.news.entity.Articles
import com.io.pet_newsapp.domain.news.repository.NewsRepository
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@BoundTo(supertype = NewsRepository::class, component = SingletonComponent::class)
class NewsRepositoryImpl @Inject constructor(
    private val pagingSourceFactory: NewsPagingSource.Factory
) : NewsRepository {

    override fun getNews(q: String): Flow<PagingData<Articles>> =
        Pager(config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 3
        ),
            pagingSourceFactory = { pagingSourceFactory.create(q) }
        ).flow
}