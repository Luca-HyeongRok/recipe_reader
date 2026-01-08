package com.example.myapplication.recipereader.presentation.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactsViewModel : ViewModel() {
    // TODO: Wire ContactsProvider + permission state.
    private val _state = MutableStateFlow(ContactsUiState())
    val state: StateFlow<ContactsUiState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ContactsUiEffect>()
    val effect: SharedFlow<ContactsUiEffect> = _effect.asSharedFlow()

    fun onEvent(event: ContactsUiEvent) {
        when (event) {
            is ContactsUiEvent.OnSearchQueryChange -> {
                // TODO: Apply 초성 검색 filter to contacts list.
                _state.value = _state.value.copy(searchQuery = event.query)
            }
            is ContactsUiEvent.OnContactClick -> {
                viewModelScope.launch {
                    _effect.emit(ContactsUiEffect.NavigateToDetail(event.id))
                }
            }
        }
    }
}
