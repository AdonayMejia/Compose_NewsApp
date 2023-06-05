package com.example.compose_newsapp.model.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.rounded.Newspaper
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object SearchScreen : BottomBarScreen(
        route = "SearchScreen",
        title = "Search News",
        icon = Icons.Rounded.Newspaper
    )

    object FavoriteScreen : BottomBarScreen(
        route = "FavoriteScreen",
        title = "Favorite News",
        icon = Icons.Rounded.Star
    )

    object DetailScreen : BottomBarScreen(
        route = "DetailScreen/{url}",
        title = "Detail",
        icon = Icons.Default.ViewList
    )
//    companion object {
//        fun String.title(): String {
//            return when (this) {
//                SearchScreen.route -> SearchScreen.title
//                FavoriteScreen.route -> FavoriteScreen.title
//                DetailScreen.route -> DetailScreen.title
//                else -> ""
//            }
//        }
//    }
}