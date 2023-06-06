package com.example.compose_newsapp.model.room.repository

import com.example.compose_newsapp.model.room.NewsEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    val favNews: Flow<List<NewsEntity>>

    suspend fun addFavNew(article: NewsEntity)

    suspend fun deleteFavArticle(id: String)

}