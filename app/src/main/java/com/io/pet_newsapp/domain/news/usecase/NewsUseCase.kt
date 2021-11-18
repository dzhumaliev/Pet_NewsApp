package com.io.pet_newsapp.domain.news.usecase

import androidx.paging.PagingData
import com.io.pet_newsapp.domain.news.entity.Articles
import com.io.pet_newsapp.domain.news.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsUseCase(private val newsRepo: NewsRepository) {

    operator fun invoke(q: String): Flow<PagingData<Articles>> = newsRepo.getNews(q)

}



