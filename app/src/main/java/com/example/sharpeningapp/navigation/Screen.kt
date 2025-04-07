package com.example.sharpeningapp.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Details: Screen("details/{username}") {
        fun createRoute(username: String) = "details/$username"
        val arguments= listOf(
            navArgument("username") {
                type = NavType.StringType
            }
        )
    }
}