package com.example.compose_newsapp.model.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_newsapp.ui.favoriteview.FavoriteScreen
import com.example.compose_newsapp.ui.searchview.SearchScreen
import com.example.compose_newsapp.ui.searchview.viewmodel.SearchViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun BottomNavGraph(
    navController:NavHostController
) {
    val searchViewModel: SearchViewModel = getViewModel()
    val uiState by searchViewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.SearchScreen.route
    ){
        composable(route = BottomBarScreen.SearchScreen.route){
            SearchScreen(
                searchViewModel = searchViewModel
            )
        }
        composable(route = BottomBarScreen.FavoriteScreen.route){
            FavoriteScreen()
        }
    }
}
