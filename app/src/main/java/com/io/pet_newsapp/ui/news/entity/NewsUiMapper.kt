package com.io.pet_newsapp.ui.news.entity

import com.io.pet_newsapp.domain.base.mapper.Mapper
import com.io.pet_newsapp.domain.news.entity.Articles

class NewsUiMapper : Mapper<Articles, ArticlesUI> {

    override fun mapLeftToRight(obj: Articles): ArticlesUI = with(obj) {
        ArticlesUI(
            author = this.author,
            title = this.title,
            description = this.description,
            urlToImage = this.urlToImage,
            publishedAt = this.publishedAt
        )
    }
}