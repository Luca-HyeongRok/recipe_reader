package com.example.myapplication.recipereader.core.navigation

sealed class Destinations(val route: String) {
    data object Recipes : Destinations("recipes")
    data object Contacts : Destinations("contacts")
    data object Gallery : Destinations("gallery")

    data object RecipeDetail : Destinations("recipes/detail/{id}") {
        fun createRoute(id: String) = "recipes/detail/$id"
    }

    data object ContactDetail : Destinations("contacts/detail/{id}") {
        fun createRoute(id: String) = "contacts/detail/$id"
    }

    data object PhotoDetail : Destinations("gallery/detail/{id}") {
        fun createRoute(id: String) = "gallery/detail/$id"
    }
}
