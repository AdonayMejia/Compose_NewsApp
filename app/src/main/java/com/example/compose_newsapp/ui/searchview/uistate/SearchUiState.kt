package com.example.compose_newsapp.ui.searchview.uistate

import androidx.paging.PagingData
import com.example.compose_newsapp.model.datamodel.NewsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

data class SearchUiState(
    val articles: List<NewsModel> = emptyList(),
    var isLoading: Boolean = false,
//    val newsPaginatedItemsProvider: StateFlow<Flow<PagingData<NewsModel>>?>,
    val onQueryChange: (String) -> Unit,
    val searchValue: StateFlow<String>
)