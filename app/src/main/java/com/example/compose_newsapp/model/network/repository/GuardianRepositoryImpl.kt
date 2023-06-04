package com.example.compose_newsapp.model.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.compose_newsapp.model.datamodel.GuardianApiResponse
import com.example.compose_newsapp.model.datamodel.NewsModel
import com.example.compose_newsapp.model.network.GuardianApiService
import com.example.compose_newsapp.model.paging.NewPagingSource
import kotlinx.coroutines.flow.Flow

class GuardianRepositoryImpl(private val apiService: GuardianApiService): GuardianRepository {
    override fun searchArticles(query: String): Flow<PagingData<NewsModel>> {
        return createPage(query).flow
    }

    private fun createPage(query: String):Pager<Int, NewsModel>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = {
                NewPagingSource(
                    apiService = apiService,
                    query = query
                )
            }
        )
    }

}