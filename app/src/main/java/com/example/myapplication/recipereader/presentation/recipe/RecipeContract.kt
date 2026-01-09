package com.example.myapplication.recipereader.presentation.recipe

import com.example.myapplication.recipereader.domain.model.Recipe

data class RecipeUiState(
    val isLoading: Boolean = false,
    val recipes: List<Recipe> = emptyList(),
    val error: String? = null
)

sealed interface RecipeUiEvent {
    data object LoadRecipes : RecipeUiEvent
    data class OnRecipeClick(val recipeId: Int) : RecipeUiEvent
}
