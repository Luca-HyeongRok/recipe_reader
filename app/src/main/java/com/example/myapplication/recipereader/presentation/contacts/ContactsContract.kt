package com.example.myapplication.recipereader.presentation.contacts

data class ContactItem(
    val id: String,
    val name: String
)

data class ContactsUiState(
    val hasPermission: Boolean = false,
    val searchQuery: String = "",
    val items: List<ContactItem> = emptyList()
)

sealed interface ContactsUiEvent {
    data class OnSearchQueryChange(val query: String) : ContactsUiEvent
    data class OnContactClick(val id: String) : ContactsUiEvent
}

sealed interface ContactsUiEffect {
    data class NavigateToDetail(val id: String) : ContactsUiEffect
}
