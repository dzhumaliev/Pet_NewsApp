package com.io.pet_newsapp.domain.news.usecase

import androidx.paging.PagingData
import com.io.pet_newsapp.domain.news.entity.Articles
import com.io.pet_newsapp.domain.news.repository.NewsRepository
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//@BoundTo(supertype = NewsRepository::class, component = SingletonComponent::class)
class NewsUseCase @Inject constructor(private val newsRepo: NewsRepository) {

    operator fun invoke(q: String): Flow<PagingData<Articles>> = newsRepo.getNews(q)

}



