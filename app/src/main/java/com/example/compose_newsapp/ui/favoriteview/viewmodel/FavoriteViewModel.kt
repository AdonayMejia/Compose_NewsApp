package com.example.compose_newsapp.ui.favoriteview.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_newsapp.model.datamodel.NewsModel
import com.example.compose_newsapp.model.room.NewsEntity
import com.example.compose_newsapp.model.room.repository.NewsRepository
import com.example.compose_newsapp.ui.favoriteview.uistate.FavoriteUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel( private val newsRepository: NewsRepository) : ViewModel() {
    private val listFavNew = MutableStateFlow<List<NewsEntity>>(emptyList())
    val new = listFavNew.asStateFlow()

    private val _uiState = MutableStateFlow(
        FavoriteUiState(
            setList = emptySet(),
            addToFav = ::addNewsToFavorite,
            deleteNew = ::deleteNews
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        getFavMovies()
    }

    private fun addNewsToFavorite(news: NewsModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val favNews = NewsEntity(
                news.id,
                news.webTitle,
                news.webUrl,
                news.fields.thumbnail
            )
            newsRepository.addFavNew(favNews)
        }
    }

    private fun deleteNews(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.deleteFavArticle(id)
        }
    }

    private fun getFavMovies(){
        viewModelScope.launch {
            newsRepository.favNews.collect{fav ->
                val favoriteNew = fav.map { it.itemId }.toSet()
                _uiState.update {
                    it.copy(
                        setList = favoriteNew
                    )
                }
                listFavNew.value = fav
            }
        }
    }

}