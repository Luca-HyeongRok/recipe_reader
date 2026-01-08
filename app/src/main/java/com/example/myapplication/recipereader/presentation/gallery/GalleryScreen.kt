package com.example.myapplication.recipereader.presentation.gallery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.recipereader.ui.EmptyState
import com.example.myapplication.recipereader.ui.PermissionDenied

@Composable
fun GalleryScreen(
    onNavigateToDetail: (String) -> Unit,
    viewModel: GalleryViewModel = viewModel()
) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is GalleryUiEffect.NavigateToDetail -> onNavigateToDetail(effect.id)
            }
        }
    }

    GalleryScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun GalleryScreenContent(
    uiState: GalleryUiState,
    onEvent: (GalleryUiEvent) -> Unit
) {
    when {
        !uiState.hasPermission -> {
            PermissionDenied(
                title = "Gallery permission",
                message = "Enable media access to continue."
            )
        }
        uiState.items.isEmpty() -> {
            EmptyState(
                title = "No photos",
                message = "MediaStore integration coming later."
            )
        }
        else -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.items) { item ->
                    ListItem(
                        headlineContent = { Text(item.title) },
                        modifier = Modifier.clickable {
                            onEvent(GalleryUiEvent.OnPhotoClick(item.id))
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GalleryScreenPreview() {
    GalleryScreenContent(
        uiState = GalleryUiState(
            items = listOf(PhotoItem("1", "Preview Photo"))
        ),
        onEvent = {}
    )
}
