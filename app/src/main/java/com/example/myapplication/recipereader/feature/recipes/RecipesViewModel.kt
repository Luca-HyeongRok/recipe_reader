package com.example.myapplication.recipereader.feature.recipes

import androidx.lifecycle.ViewModel

class RecipesViewModel : ViewModel() {
    // TODO: Connect ContentProvider/Paging for real data.
    val uiState = RecipesUiState(
        items = listOf(
            RecipeItem(id = "1", title = "Pasta"),
            RecipeItem(id = "2", title = "Salad"),
            RecipeItem(id = "3", title = "Soup")
        )
    )
}
