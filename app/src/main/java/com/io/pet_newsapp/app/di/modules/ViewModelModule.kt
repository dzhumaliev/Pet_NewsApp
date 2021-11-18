package com.io.pet_newsapp.app.di.modules

import com.io.pet_newsapp.ui.news.fragments.server.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsViewModel(get()) }

}