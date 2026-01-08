package com.example.myapplication.recipereader.presentation.recipe

data class RecipeItem(
    val id: String,
    val title: String
)

data class RecipeUiState(
    val hasPermission: Boolean = true,
    val items: List<RecipeItem> = emptyList()
)

sealed interface RecipeUiEvent {
    data class OnRecipeClick(val id: String) : RecipeUiEvent
}

sealed interface RecipeUiEffect {
    data class NavigateToDetail(val id: String) : RecipeUiEffect
}
