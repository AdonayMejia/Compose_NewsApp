package com.example.compose_newsapp.ui.searchview.repository

import com.example.compose_newsapp.model.datamodel.GuardianApiResponse

interface SearchRepository {
    suspend fun searchNews(query: String): GuardianApiResponse
}