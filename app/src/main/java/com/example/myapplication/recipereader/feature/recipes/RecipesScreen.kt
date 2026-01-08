package com.example.myapplication.recipereader.feature.recipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.recipereader.core.ui.EmptyState
import com.example.myapplication.recipereader.core.ui.PermissionDenied

@Composable
fun RecipesScreen(
    onItemClick: (String) -> Unit,
    viewModel: RecipesViewModel = viewModel()
) {
    RecipesScreenContent(
        uiState = viewModel.uiState,
        onItemClick = onItemClick
    )
}

@Composable
fun RecipesScreenContent(
    uiState: RecipesUiState,
    onItemClick: (String) -> Unit
) {
    when {
        !uiState.hasPermission -> {
            PermissionDenied(
                title = "Permission required",
                message = "Recipe permissions are required."
            )
        }
        uiState.items.isEmpty() -> {
            EmptyState(
                title = "No recipes",
                message = "Add recipes later."
            )
        }
        else -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.items) { item ->
                    ListItem(
                        headlineContent = { Text(item.title) },
                        modifier = Modifier.clickable { onItemClick(item.id) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipesScreenPreview() {
    RecipesScreenContent(
        uiState = RecipesUiState(
            items = listOf(RecipeItem("1", "Preview Recipe"))
        ),
        onItemClick = {}
    )
}
