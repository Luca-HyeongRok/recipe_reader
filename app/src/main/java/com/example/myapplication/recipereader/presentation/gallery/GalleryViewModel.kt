package com.example.myapplication.recipereader.presentation.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GalleryViewModel : ViewModel() {
    // TODO: Hook MediaStore + Paging3 for gallery list.
    private val _state = MutableStateFlow(GalleryUiState())
    val state: StateFlow<GalleryUiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<GalleryUiEffect>()
    val effect: SharedFlow<GalleryUiEffect> = _effect.asSharedFlow()

    fun onEvent(event: GalleryUiEvent) {
        when (event) {
            is GalleryUiEvent.OnPhotoClick -> {
                viewModelScope.launch {
                    _effect.emit(GalleryUiEffect.NavigateToDetail(event.id))
                }
            }
        }
    }
}
