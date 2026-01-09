package com.example.myapplication.recipereader.presentation.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.myapplication.recipereader.domain.model.Recipe

@Composable
fun RecipeScreen(
    onRecipeClick: (Int) -> Unit,
    viewModel: RecipeViewModel = viewModel(
        factory = RecipeViewModelFactory(LocalContext.current.contentResolver)
    )
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(RecipeUiEvent.LoadRecipes)
    }

    RecipeScreenContent(
        state = state,
        onRecipeClick = onRecipeClick
    )
}

@Composable
fun RecipeScreenContent(
    state: RecipeUiState,
    onRecipeClick: (Int) -> Unit
) {
    val background = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFDF9F3),
            Color(0xFFF6F0E6),
            Color(0xFFEFE7DB)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
    ) {
        when {
            state.isLoading -> {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFF8C6A4A))
                }
            }

            state.recipes.isEmpty() -> {
                EmptyRecipeState()
            }

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 24.dp)
                ) {
                    item {
                        RecipeHeader(count = state.recipes.size)
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    items(state.recipes) { recipe ->
                        RecipeCard(
                            recipe = recipe,
                            onClick = { onRecipeClick(recipe.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyRecipeState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No bookmarked recipes",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF5C4A3D)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Save your favorites to see them here.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF7C6A5C)
        )
    }
}

@Composable
private fun RecipeHeader(count: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Saved Recipes",
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF3C2F27)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "$count bookmarked",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF6B584A)
        )
    }
}

@Composable
private fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFCF9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.size(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = recipe.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF2E2420)
                )
                Spacer(modifier = Modifier.height(6.dp))
                MetaRow(
                    icon = Icons.Rounded.Person,
                    text = recipe.chef
                )
                MetaRow(
                    icon = Icons.Rounded.Schedule,
                    text = recipe.time
                )
                MetaRow(
                    icon = Icons.Rounded.Star,
                    text = recipe.rating.toString(),
                    iconTint = Color(0xFFD19A2A)
                )
            }
        }
    }
}

@Composable
private fun MetaRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    iconTint: Color = Color(0xFF8B7A6D)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF5A4A40)
        )
    }
}
