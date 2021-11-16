package com.io.pet_newsapp.ui.news.server

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.io.pet_newsapp.domain.news.usecase.NewsUseCase
import com.io.pet_newsapp.ui.news.entity.ArticlesUI
import com.io.pet_newsapp.ui.news.entity.NewsUiMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class NewsViewModel(
    private val useCase: NewsUseCase

) : ViewModel() {

//    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData()
//    val loadingState: LiveData<Boolean> = _loadingState

    private val _news =
        MutableStateFlow<PagingData<ArticlesUI>>(PagingData.empty())
    val news: Flow<PagingData<ArticlesUI>> = _news

    private val _loadingState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = _loadingState

    init {
        getNews()
    }

    private fun getProductsByCoroutinePath() =
        useCase.invoke("tesla")
            .cachedIn(viewModelScope)

    private fun getNews() = viewModelScope.launch {


        viewModelScope.launch {
            _news.value = getProductsByCoroutinePath().first().map {
                NewsUiMapper().mapLeftToRight(it)
            }
        }
    }
}