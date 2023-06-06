package com.example.compose_newsapp.model.network

import com.example.compose_newsapp.BuildConfig
import com.example.compose_newsapp.model.datamodel.Filter
import com.example.compose_newsapp.model.datamodel.GuardianApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GuardianApiImpl(private val client: HttpClient) : GuardianApiService {

    override suspend fun searchArticles(
        query: String,
        page: Int,
        pageSize: Int,
        filter: Filter
    ): GuardianApiResponse {
        return client.get("https://content.guardianapis.com/search") {
            parameter(QUERY, query)
            parameter(KEY, BuildConfig.GUARDIAN_API_KEY)
            parameter(PAGE_SIZE, pageSize)
            parameter(PAGE, page)
            parameter(SHOW_FIELDS, THUMBNAIL)
            filter.section?.let { parameter(SECTION, it) }
            filter.tag?.let { parameter(TAG, it) }
            filter.type?.let { parameter(TYPE, it) }
        }.body()
    }

    companion object {
        const val KEY = "api-key"
        const val QUERY = "q"
        const val PAGE = "page"
        const val PAGE_SIZE = "page-size"
        const val SHOW_FIELDS = "show-fields"
        const val THUMBNAIL = "thumbnail"
        const val SECTION = "section"
        const val TAG = "tag"
        const val TYPE = "type"
    }


}