package com.dicoding.mushroom.Navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Detail : Screen("detail/{mushromId}") {
        fun createRoute(mushromId: Long) = "detail/$mushromId"
    }
}