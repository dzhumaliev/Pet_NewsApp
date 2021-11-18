package com.io.pet_newsapp.app.di.modules

import com.io.pet_newsapp.domain.news.usecase.NewsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { NewsUseCase(get()) }
}