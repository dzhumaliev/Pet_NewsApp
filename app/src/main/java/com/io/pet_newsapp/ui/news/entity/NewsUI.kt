package com.io.pet_newsapp.ui.news.entity

data class NewsUI(
    var articles: List<ArticlesUI> = listOf()
)

data class ArticlesUI(
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null
)