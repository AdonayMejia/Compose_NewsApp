package com.example.compose_newsapp.model.network

import com.example.compose_newsapp.model.datamodel.Filter
import com.example.compose_newsapp.model.datamodel.GuardianApiResponse

interface GuardianApiService {
    suspend fun searchArticles(
        query: String,
        page: Int,
        pageSize: Int,
        filter: Filter
    ): GuardianApiResponse
}