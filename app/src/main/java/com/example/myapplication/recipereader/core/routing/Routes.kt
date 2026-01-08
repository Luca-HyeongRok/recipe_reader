package com.example.myapplication.recipereader.core.routing

sealed class Route(val route: String) {
    data object Recipes : Route("recipes")
    data object Contacts : Route("contacts")
    data object Gallery : Route("gallery")

    data object RecipeDetail : Route("recipes/detail/{id}") {
        fun createRoute(id: String) = "recipes/detail/$id"
    }

    data object ContactDetail : Route("contacts/detail/{id}") {
        fun createRoute(id: String) = "contacts/detail/$id"
    }

    data object PhotoDetail : Route("gallery/detail/{id}") {
        fun createRoute(id: String) = "gallery/detail/$id"
    }
}
