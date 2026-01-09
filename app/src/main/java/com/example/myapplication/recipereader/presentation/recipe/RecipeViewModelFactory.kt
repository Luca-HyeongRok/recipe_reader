package com.example.myapplication.recipereader.presentation.recipe

import android.content.ContentResolver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.recipereader.data.datasource.RecipeContentDataSource
import com.example.myapplication.recipereader.data.datasource.RecipeMockDataSource
import com.example.myapplication.recipereader.data.repository.RecipeRepositoryImpl

class RecipeViewModelFactory(
    private val contentResolver: ContentResolver
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val contentDS = RecipeContentDataSource(contentResolver)
        val mockDS = RecipeMockDataSource()
        val repository = RecipeRepositoryImpl(contentDS, mockDS)

        @Suppress("UNCHECKED_CAST")
        return RecipeViewModel(repository) as T
    }
}
