package com.example.compose_newsapp.ui.searchview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.compose_newsapp.model.datamodel.Filter
import com.example.compose_newsapp.model.datamodel.NewsModel
import com.example.compose_newsapp.model.network.repository.GuardianRepository
import com.example.compose_newsapp.ui.searchview.uistate.SearchUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest

class SearchViewModel(private val guardianRepository: GuardianRepository) : ViewModel() {
    private val searchValue = MutableStateFlow("")
    private var filters = MutableStateFlow(Filter(""))

    @OptIn(ExperimentalCoroutinesApi::class)
    val articlesFlow: Flow<PagingData<NewsModel>> = combine(searchValue, filters) { query, filter ->
        Pair(query, filter)
    }.flatMapLatest { (query, filter) ->
        guardianRepository.searchArticles(query, filter)
    }.cachedIn(viewModelScope)

    private fun searchNews(query: String, filter: Filter) {
        searchValue.value = query
        filters.value = filter
    }

    val uiState = MutableStateFlow(
        SearchUiState(
            news = emptyList(),
            isLoading = false,
            searchNews = ::searchNews,
        )
    )

}