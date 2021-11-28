package com.io.pet_newsapp.app.di.modules

import com.io.pet_newsapp.data.news.datasource.NewsPagingSource
import com.io.pet_newsapp.data.news.repository.NewsRepositoryImpl
import com.io.pet_newsapp.domain.news.repository.NewsRepository
import com.io.pet_newsapp.domain.news.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

//    @Provides
//    @ViewModelScoped
//    fun newsRepo(
//        pagingSource: NewsPagingSource.Factory,
//        q: String
//    ): NewsRepository = NewsRepositoryImpl(pagingSource)

    @Provides
    fun provideNewsUseCase(repository: NewsRepositoryImpl) : NewsUseCase{
        return NewsUseCase(repository)
    }

}