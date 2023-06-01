package com.example.compose_newsapp.model.navigation.components

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.compose_newsapp.model.navigation.BottomBarScreen

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.SearchScreen,
        BottomBarScreen.FavoriteScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            Item(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}