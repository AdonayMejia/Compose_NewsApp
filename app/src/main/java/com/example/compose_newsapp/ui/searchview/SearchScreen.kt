package com.example.compose_newsapp.ui.searchview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.compose_newsapp.ui.favoriteview.viewmodel.FavoriteViewModel
import com.example.compose_newsapp.ui.searchview.components.SearchScreenContent
import com.example.compose_newsapp.ui.searchview.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel,
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController
) {
    val uiState by searchViewModel.uiState.collectAsState()
    val articles = searchViewModel.articlesFlow.collectAsLazyPagingItems()
    val favUiState by favoriteViewModel.uiState.collectAsState()

    SearchScreenContent(
        searchNew = uiState.searchNews,
        articles = articles,
        isLoading = uiState.isLoading,
        onFavClick = favUiState.addToFav,
        navHostController = navController
    )
}
