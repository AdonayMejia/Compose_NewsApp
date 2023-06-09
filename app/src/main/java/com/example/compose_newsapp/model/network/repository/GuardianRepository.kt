package com.example.compose_newsapp.model.network.repository

import androidx.paging.PagingData
import com.example.compose_newsapp.model.datamodel.Filter
import com.example.compose_newsapp.model.datamodel.NewsModel
import kotlinx.coroutines.flow.Flow

interface GuardianRepository {
    fun searchArticles(query: String, filter: Filter): Flow<PagingData<NewsModel>>
}