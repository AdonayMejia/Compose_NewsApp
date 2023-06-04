package com.example.compose_newsapp.ui.searchview.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.compose_newsapp.model.datamodel.NewsModel
import com.example.compose_newsapp.model.network.GuardianApiService
import com.example.compose_newsapp.model.network.repository.GuardianRepository
import com.example.compose_newsapp.ui.searchview.uistate.SearchUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchViewModel( private val guardianRepository: GuardianRepository) : ViewModel() {
    private val searchValue = MutableStateFlow("")
    private var isLoading = MutableStateFlow(false)

//
//    private val newsPaginatedItemProvider = combine(
//        searchValue
//    ){ query ->
//        if (query.isNotEmpty()){
//            isLoading.value = false
//            guardianRepository.searchArticles(query = query.toString())
//        } else{
//            null
//        }
//    }.stateIn(viewModelScope, SharingStarted.Lazily,null)

    private fun onQueryChange(query: String) {
        isLoading.value = true
        searchValue.value = query
//            if (query.length == 1) query.trim() else query
        isLoading.value = false
        Log.wtf("Value","Value: ${searchValue.value}")
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    val articlesFlow: Flow<PagingData<NewsModel>> = searchValue.flatMapLatest { query ->
        guardianRepository.searchArticles(query)
    }.cachedIn(viewModelScope)


    private val _uiState = MutableStateFlow(
        SearchUiState(
            articles = emptyList(),
            isLoading = false,
            onQueryChange = ::onQueryChange,
            searchValue = searchValue
        )
    )
    val uiState = _uiState.asStateFlow()

//    fun searchArticles(query:String){
//        viewModelScope.launch {
//            _uiState.value = uiState.value.copy(isLoading = true)
//            val response = guardianRepository.searchArticles(query)
//            _uiState.value = SearchUiState(
//                articles = response.response.results,
//                isLoading = false
//            )
//        }
//    }
}