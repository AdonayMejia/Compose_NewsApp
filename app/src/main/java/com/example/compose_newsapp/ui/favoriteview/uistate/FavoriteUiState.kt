package com.example.compose_newsapp.ui.favoriteview.uistate

import com.example.compose_newsapp.model.datamodel.NewsModel

data class FavoriteUiState(
    var setList: Set<String> = emptySet(),
    val addToFav:(NewsModel) -> Unit,
    val deleteNew:(String) -> Unit)