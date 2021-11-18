package com.io.pet_newsapp.data.news.entity

import com.io.pet_newsapp.domain.base.mapper.Mapper
import com.io.pet_newsapp.domain.news.entity.Articles
import com.io.pet_newsapp.domain.news.entity.News

class NewsMapper : Mapper<NewsModel, News> {
    override fun mapLeftToRight(obj: NewsModel): News = with(obj) {
        News(
            articles = articles.map {
                Articles(
                    author = it.author,
                    title = it.title,
                    description = it.description,
                    urlToImage = it.urlToImage,
                    publishedAt = it.publishedAt
                )
            }
        )
    }
}