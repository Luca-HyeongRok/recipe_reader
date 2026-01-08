package com.example.myapplication.recipereader.feature.gallery

import androidx.lifecycle.ViewModel

class GalleryViewModel : ViewModel() {
    // TODO: Connect MediaStore + permissions.
    val uiState = GalleryUiState(
        hasPermission = true,
        items = emptyList()
    )
}
