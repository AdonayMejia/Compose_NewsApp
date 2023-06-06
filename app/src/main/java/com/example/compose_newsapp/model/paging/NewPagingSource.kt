package com.example.compose_newsapp.model.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.compose_newsapp.model.datamodel.Filter
import com.example.compose_newsapp.model.datamodel.NewsModel
import com.example.compose_newsapp.model.network.GuardianApiService

class NewPagingSource(
    private val apiService: GuardianApiService,
    private val query: String,
    private val filter: Filter
) : PagingSource<Int, NewsModel>() {

    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsModel> {
        return try {
            val page = params.key ?: 1
            val response = apiService.searchArticles(
                query = query,
                page = page,
                pageSize = params.loadSize,
                filter = filter
            )

            LoadResult.Page(
                data = response.response.results,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.response.results.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}