package com.io.pet_newsapp.data.news.datasource

import androidx.annotation.Nullable
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.io.pet_newsapp.data.network.NetworkResponse
import com.io.pet_newsapp.data.news.entity.NewsMapper
import com.io.pet_newsapp.data.news.remote.Api
import com.io.pet_newsapp.domain.base.Failure
import com.io.pet_newsapp.domain.news.entity.Articles
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

private const val STARTING_PAGE_INDEX = 1
private const val PAGE_SIZE = 20

class NewsPagingSource(private var api: Api, val q: String) : PagingSource<Int, Articles>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Articles> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = api.getNews(q, position, PAGE_SIZE).let {
                NewsMapper().mapLeftToRight(it)
            }

            toLoadResult(response.articles, position)
        } catch (e: Exception) {
//            LoadResult.Error(Throwable(e.message))
            when (e) {
                is UnknownHostException, is SocketTimeoutException -> {
                    LoadResult.Error(
                        Failure.NoInternet(e.message)
                    )
                }
                is TimeoutException -> {
                    LoadResult.Error(
                        Failure.Timeout(e.message)
                    )
                }
                else -> {
                    LoadResult.Error(
                        Failure.Unknown(e.message)
                    )
                }
            }
        }


    }

    override fun getRefreshKey(state: PagingState<Int, Articles>): Int? = state.anchorPosition

    private fun toLoadResult(
        @Nullable response: List<Articles>,
        position: Int
    ): LoadResult<Int, Articles> {
        return LoadResult.Page(
            data = response,
            prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
            nextKey = if (response.isEmpty()) null else position + 1,
            itemsBefore = LoadResult.Page.COUNT_UNDEFINED,
            itemsAfter = LoadResult.Page.COUNT_UNDEFINED   //read
        )
    }


}