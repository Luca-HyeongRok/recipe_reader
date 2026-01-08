package com.example.myapplication.recipereader.presentation.recipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.recipereader.ui.EmptyState
import com.example.myapplication.recipereader.ui.PermissionDenied

@Composable
fun RecipeScreen(
    onNavigateToDetail: (String) -> Unit,
    viewModel: RecipeViewModel = viewModel()
) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is RecipeUiEffect.NavigateToDetail -> onNavigateToDetail(effect.id)
            }
        }
    }

    RecipeScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun RecipeScreenContent(
    uiState: RecipeUiState,
    onEvent: (RecipeUiEvent) -> Unit
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
                message = "Load recipes from ContentProvider later."
            )
        }
        else -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.items) { item ->
                    ListItem(
                        headlineContent = { Text(item.title) },
                        modifier = Modifier.clickable {
                            onEvent(RecipeUiEvent.OnRecipeClick(item.id))
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeScreenPreview() {
    RecipeScreenContent(
        uiState = RecipeUiState(
            items = listOf(RecipeItem("1", "Preview Recipe"))
        ),
        onEvent = {}
    )
}
