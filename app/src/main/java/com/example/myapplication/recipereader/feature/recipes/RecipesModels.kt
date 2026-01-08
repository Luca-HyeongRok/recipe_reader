package com.example.myapplication.recipereader.feature.recipes

data class RecipeItem(
    val id: String,
    val title: String
)

data class RecipesUiState(
    val hasPermission: Boolean = true,
    val items: List<RecipeItem> = emptyList()
)
