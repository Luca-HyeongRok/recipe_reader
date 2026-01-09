package com.example.myapplication.recipereader.data.datasource

import android.content.ContentResolver
import android.net.Uri

class RecipeContentDataSource(
    private val contentResolver: ContentResolver
) {

    companion object {
        private const val AUTHORITY =
            "com.survivalcoding.gangnam2kiandroidstudy.provider"
        private const val PATH = "bookmark"
        private const val COLUMN_RECIPE_ID = "recipe_id"

        private val URI =
            Uri.parse("content://$AUTHORITY/$PATH")
    }

    fun getBookmarkedRecipeIds(): List<Int> {
        val result = mutableListOf<Int>()

        val cursor = contentResolver.query(
            URI,
            arrayOf(COLUMN_RECIPE_ID),
            null,
            null,
            null
        ) ?: return emptyList()

        cursor.use {
            val index = it.getColumnIndex(COLUMN_RECIPE_ID)
            if (index == -1) return emptyList()

            while (it.moveToNext()) {
                result.add(it.getInt(index))
            }
        }
        return result
    }
}
