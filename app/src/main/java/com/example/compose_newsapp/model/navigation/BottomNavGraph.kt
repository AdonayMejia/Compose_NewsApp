package com.example.compose_newsapp.model.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_newsapp.ui.favoriteview.FavoriteScreen
import com.example.compose_newsapp.ui.searchview.SearchScreen

@Composable
fun BottomNavGraph(
    navController:NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.SearchScreen.route
    ){
        composable(route = BottomBarScreen.SearchScreen.route){
            SearchScreen()
        }
        composable(route = BottomBarScreen.FavoriteScreen.route){
            FavoriteScreen()
        }
    }
}
