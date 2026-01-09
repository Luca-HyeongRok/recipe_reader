package com.example.myapplication.recipereader.data.repository

import com.example.myapplication.recipereader.domain.model.Recipe

interface RecipeRepository {
    fun getBookmarkedRecipes(): List<Recipe>
}
