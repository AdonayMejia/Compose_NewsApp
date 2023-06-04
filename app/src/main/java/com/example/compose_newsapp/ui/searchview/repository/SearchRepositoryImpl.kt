package com.example.compose_newsapp.ui.searchview.repository

import com.example.compose_newsapp.model.datamodel.GuardianApiResponse
import com.example.compose_newsapp.model.datamodel.NewsModel
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl : SearchRepository {
    override suspend fun searchNews(query: String): GuardianApiResponse {
        TODO("Not yet implemented")
    }

}