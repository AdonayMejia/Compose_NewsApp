package com.example.compose_newsapp.ui.favoriteview

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.compose_newsapp.ui.favoriteview.components.FavoriteScreenContent
import com.example.compose_newsapp.ui.favoriteview.viewmodel.FavoriteViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    navHostController: NavHostController
) {
    FavoriteScreenContent(
        viewModel = viewModel,
        navHostController = navHostController
    )
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun FavoritePreview() {
//    FavoriteScreenContent(viewModel = viewModel )
//}