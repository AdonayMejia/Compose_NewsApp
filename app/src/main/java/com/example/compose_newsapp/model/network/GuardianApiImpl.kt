package com.example.compose_newsapp.model.network

import com.example.compose_newsapp.BuildConfig
import com.example.compose_newsapp.model.datamodel.GuardianApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.append

class GuardianApiImpl(private val client:HttpClient) : GuardianApiService {

    override suspend fun searchArticles(query: String, page: Int, pageSize:Int): GuardianApiResponse {
        return client.get(BASE_URL){
            parameter(QUERY,query)
            parameter(PAGE,page)
            parameter(PAGE_SIZE,pageSize)
        }.body()
    }

    companion object{
        const val KEY = "key"
        const val BASE_URL = "search"
        const val QUERY = "q"
        const val PAGE = "page"
        const val PAGE_SIZE = "page-size"
        const val thumbnail = "show-fields"
    }

}