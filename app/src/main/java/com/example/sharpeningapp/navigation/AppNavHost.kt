package com.example.sharpeningapp.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sharpeningapp.ui.screens.DetailsScreen.DetailsScreen
import com.example.sharpeningapp.ui.screens.HomeScreen.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onNavigateToDetails = { username ->
                    navController.navigate(Screen.Details.createRoute(username))
                },
                modifier = Modifier
            )
        }

        composable(
            route = Screen.Details.route,
            arguments = Screen.Details.arguments
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            DetailsScreen(
                onNavigateBack = { navController.popBackStack() },
                modifier = Modifier,
                playerName = username
            )
        }
    }
}