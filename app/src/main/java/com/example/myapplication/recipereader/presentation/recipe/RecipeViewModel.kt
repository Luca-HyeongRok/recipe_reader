package com.example.myapplication.recipereader.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    // TODO: Connect ContentResolver to load recipe IDs.
    private val _state = MutableStateFlow(
        RecipeUiState(
            items = listOf(
                RecipeItem("1", "Pasta"),
                RecipeItem("2", "Salad"),
                RecipeItem("3", "Soup")
            )
        )
    )
    val state: StateFlow<RecipeUiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<RecipeUiEffect>()
    val effect: SharedFlow<RecipeUiEffect> = _effect.asSharedFlow()

    fun onEvent(event: RecipeUiEvent) {
        when (event) {
            is RecipeUiEvent.OnRecipeClick -> {
                viewModelScope.launch {
                    _effect.emit(RecipeUiEffect.NavigateToDetail(event.id))
                }
            }
        }
    }
}
