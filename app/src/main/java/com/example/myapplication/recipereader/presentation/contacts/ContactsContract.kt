package com.example.myapplication.recipereader.presentation.contacts

import com.example.myapplication.recipereader.domain.model.Contact

data class ContactsUiState(
    val hasPermission: Boolean = false,
    val isUsingMock: Boolean = false,
    val searchQuery: String = "",
    val items: List<Contact> = emptyList()
)

sealed interface ContactsUiEvent {
    data object LoadContacts : ContactsUiEvent
    data class OnSearchQueryChange(val query: String) : ContactsUiEvent
    data class OnContactClick(val id: String) : ContactsUiEvent
    data class OnToggleFavorite(val id: String, val isFavorite: Boolean) : ContactsUiEvent
}

sealed interface ContactsUiEffect {
    data class NavigateToDetail(val id: String) : ContactsUiEffect
}
