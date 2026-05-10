package com.pjasoft.mmorenomusicapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pjasoft.mmorenomusicapp.screens.DetailScreen
import com.pjasoft.mmorenomusicapp.screens.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") {
            HomeScreen(navController = navController)
        }
        composable(route = "detail/{albumId}") { backStack ->
            val albumId = backStack.arguments?.getString("albumId") ?: ""
            DetailScreen(albumId = albumId, navController = navController)
        }
    }
}