package com.example.compose_newsapp.ui.searchview.uistate

import androidx.paging.PagingData
import com.example.compose_newsapp.model.datamodel.Filter
import com.example.compose_newsapp.model.datamodel.NewsModel
import com.example.compose_newsapp.model.room.NewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

data class SearchUiState(
    val news: List<NewsModel> = emptyList(),
    var isLoading: Boolean = false,
    val searchNews: (String, Filter) -> Unit,
    val saveSelectedFilter:(Filter) -> Unit,
    val selectedFilter:Flow<Filter>
)