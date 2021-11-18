package com.io.pet_newsapp.app.di.modules


import com.io.pet_newsapp.data.news.repository.NewsRepositoryImpl
import com.io.pet_newsapp.domain.news.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<NewsRepository> { NewsRepositoryImpl(get()) }

}