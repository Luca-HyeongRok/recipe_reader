package com.example.myapplication.recipereader.data.resolver

import android.content.ContentResolver

class RecipeContentResolver(
    private val contentResolver: ContentResolver
) {
    // TODO: Query external ContentProvider to fetch recipe IDs.
    fun getRecipeIds(): List<String> = emptyList()
}
