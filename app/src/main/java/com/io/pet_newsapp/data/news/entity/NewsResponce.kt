package com.io.pet_newsapp.data.news.entity

data class NewsModel(
    var status: String,
    var totalResults: Int,
    var articles: List<ArticlesModel> = listOf()
)

data class ArticlesModel(
    var source: LinkModel? = null,
    var author: String? = null,
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    var urlToImage: String? = null,
    var publishedAt: String? = null,
    var content: String? = null
)

data class LinkModel(
    var id: String,
    var name: String
)

