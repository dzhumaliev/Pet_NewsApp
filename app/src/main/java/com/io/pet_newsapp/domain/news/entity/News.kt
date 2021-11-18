package com.io.pet_newsapp.domain.news.entity

data class News(
    var articles: List<Articles> = listOf()
)

data class Articles(
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null
)