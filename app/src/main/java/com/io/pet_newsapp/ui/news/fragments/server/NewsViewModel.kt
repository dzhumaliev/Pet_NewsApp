package com.io.pet_newsapp.ui.news.fragments.server

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.io.pet_newsapp.domain.base.Failure
import com.io.pet_newsapp.domain.news.usecase.NewsUseCase
import com.io.pet_newsapp.ui.news.entity.ArticlesUI
import com.io.pet_newsapp.ui.news.entity.NewsUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCase: NewsUseCase
) : ViewModel() {

    private val _news =
        MutableStateFlow<PagingData<ArticlesUI>>(PagingData.empty())
    val news: Flow<PagingData<ArticlesUI>> = _news

    private val _loadingState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _failure: Channel<Failure> = Channel(Channel.BUFFERED)
    val failure: Flow<Failure> = _failure.receiveAsFlow()

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

    fun handleFailure(throwable: Throwable, retryAction: () -> Unit) {
        val failure = when (throwable) {
            is Failure.NoInternet -> {
                Failure.NoInternet("No Internet Connection")
            }
            is Failure.Api -> {
                Failure.Api(throwable.msg)
            }
            is Failure.Timeout -> {
                Failure.Timeout("Oops! Slow Connection")
            }
            is Failure.Unknown -> {
                Failure.Unknown("Unknown Error! That’s all we know")
            }
            else -> {
                Failure.Unknown("Unknown Error! That’s all we know")
            }
        }

        failure.retryAction = retryAction
        viewModelScope.launch {
            _failure.send(failure)
        }
    }
}