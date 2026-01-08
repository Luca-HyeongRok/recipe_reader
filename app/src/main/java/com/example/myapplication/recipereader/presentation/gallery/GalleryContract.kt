package com.example.myapplication.recipereader.presentation.gallery

data class PhotoItem(
    val id: String,
    val title: String
)

data class GalleryUiState(
    val hasPermission: Boolean = true,
    val items: List<PhotoItem> = emptyList()
)

sealed interface GalleryUiEvent {
    data class OnPhotoClick(val id: String) : GalleryUiEvent
}

sealed interface GalleryUiEffect {
    data class NavigateToDetail(val id: String) : GalleryUiEffect
}
