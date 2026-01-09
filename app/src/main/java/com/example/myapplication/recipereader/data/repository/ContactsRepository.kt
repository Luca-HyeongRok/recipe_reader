package com.example.myapplication.recipereader.data.repository

import com.example.myapplication.recipereader.domain.model.Contact

data class ContactsLoadResult(
    val hasPermission: Boolean,
    val isUsingMock: Boolean,
    val contacts: List<Contact>
)

interface ContactsRepository {
    suspend fun loadContacts(): ContactsLoadResult
    suspend fun setFavorite(contactId: String, isFavorite: Boolean)
}
