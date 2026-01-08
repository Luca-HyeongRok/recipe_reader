package com.example.myapplication.recipereader.feature.gallery

data class PhotoItem(
    val id: String,
    val title: String
)

data class GalleryUiState(
    val hasPermission: Boolean = true,
    val items: List<PhotoItem> = emptyList()
)
