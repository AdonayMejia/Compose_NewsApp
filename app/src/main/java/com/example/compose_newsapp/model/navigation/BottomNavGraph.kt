package com.example.compose_newsapp.model.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.compose_newsapp.ui.detailview.DetailScreen
import com.example.compose_newsapp.ui.favoriteview.FavoriteScreen
import com.example.compose_newsapp.ui.favoriteview.viewmodel.FavoriteViewModel
import com.example.compose_newsapp.ui.searchview.SearchScreen
import com.example.compose_newsapp.ui.searchview.viewmodel.SearchViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    val searchViewModel: SearchViewModel = getViewModel()
    val favoriteViewModel: FavoriteViewModel = getViewModel()

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.SearchScreen.route
    ) {
        composable(route = BottomBarScreen.SearchScreen.route) {
            SearchScreen(
                searchViewModel = searchViewModel,
                favoriteViewModel = favoriteViewModel,
                navController = navController
            )
        }
        composable(route = BottomBarScreen.FavoriteScreen.route) {
            FavoriteScreen(
                viewModel = favoriteViewModel,
                navHostController = navController
            )
        }
        composable(
            route = BottomBarScreen.DetailScreen.route,
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            DetailScreen(webUrl = url)
        }
    }
}
