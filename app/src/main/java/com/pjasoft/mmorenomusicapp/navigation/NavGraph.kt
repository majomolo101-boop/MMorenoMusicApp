package com.pjasoft.mmorenomusicapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pjasoft.mmorenomusicapp.screens.DetailScreen
import com.pjasoft.mmorenomusicapp.screens.HomeScreen
import kotlinx.serialization.Serializable


@Serializable
data class DetailRoute(val albumId: String)

@Serializable
object HomeRoute

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeScreen(navController = navController)
        }
        composable<DetailRoute> { backStack ->
            val route = backStack.toRoute<DetailRoute>()
            DetailScreen(albumId = route.albumId, navController = navController)
        }
    }
}