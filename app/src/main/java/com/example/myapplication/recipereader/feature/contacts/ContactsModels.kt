package com.example.myapplication.recipereader.feature.contacts

data class ContactItem(
    val id: String,
    val name: String
)

data class ContactsUiState(
    val hasPermission: Boolean = false,
    val items: List<ContactItem> = emptyList()
)
