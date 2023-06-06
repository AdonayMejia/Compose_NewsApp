package com.example.compose_newsapp.model.room.repository

import com.example.compose_newsapp.model.room.NewsDao
import com.example.compose_newsapp.model.room.NewsEntity
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newsDao: NewsDao) : NewsRepository {
    override val favNews: Flow<List<NewsEntity>> = newsDao.getFavoriteNews()

    override suspend fun addFavNew(article: NewsEntity) {
        return newsDao.addItem(article)
    }

    override suspend fun deleteFavArticle(id: String) {
        return newsDao.deleteItem(id)
    }
}