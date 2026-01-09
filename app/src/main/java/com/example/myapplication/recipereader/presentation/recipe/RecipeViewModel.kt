package com.example.myapplication.recipereader.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.recipereader.data.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RecipeUiState())
    val state: StateFlow<RecipeUiState> = _state.asStateFlow()

    fun onEvent(event: RecipeUiEvent) {
        when (event) {
            RecipeUiEvent.LoadRecipes -> loadRecipes()
            is RecipeUiEvent.OnRecipeClick -> Unit
        }
    }

    private fun loadRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val recipes = repository.getBookmarkedRecipes()

                _state.value = RecipeUiState(
                    isLoading = false,
                    recipes = recipes
                )
            } catch (e: Exception) {
                _state.value = RecipeUiState(
                    isLoading = false,
                    recipes = emptyList(),
                    error = e.message
                )
            }
        }
    }
}
