package com.example.myapplication.recipereader.feature.contacts

import androidx.lifecycle.ViewModel

class ContactsViewModel : ViewModel() {
    // TODO: Connect ContactsProvider + permissions.
    val uiState = ContactsUiState(
        hasPermission = false,
        items = emptyList()
    )
}
