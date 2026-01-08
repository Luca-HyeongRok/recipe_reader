package com.example.myapplication.recipereader.feature.recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RecipeDetailScreen(id: String) {
    // TODO: Load detail via ContentProvider.
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Recipe Detail", style = MaterialTheme.typography.titleLarge)
        Text(text = "id = $id", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeDetailScreenPreview() {
    RecipeDetailScreen(id = "1")
}
