package com.example.myapplication.recipereader.data.repository

import com.example.myapplication.recipereader.data.datasource.RecipeContentDataSource
import com.example.myapplication.recipereader.data.datasource.RecipeMockDataSource
import com.example.myapplication.recipereader.domain.model.Recipe

class RecipeRepositoryImpl(
    private val contentDataSource: RecipeContentDataSource,
    private val mockDataSource: RecipeMockDataSource
) : RecipeRepository {

    override fun getBookmarkedRecipes(): List<Recipe> {
        val bookmarkedIds = contentDataSource.getBookmarkedRecipeIds()
        if (bookmarkedIds.isEmpty()) return emptyList()

        return mockDataSource.getAllRecipes()
            .filter { it.id in bookmarkedIds }
    }
}
