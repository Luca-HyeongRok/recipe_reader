package com.example.myapplication.recipereader.data.repository

import com.example.myapplication.recipereader.data.datasource.ContactsFavoritesDataSource
import com.example.myapplication.recipereader.data.datasource.ContactsMockDataSource
import com.example.myapplication.recipereader.data.datasource.ContactsPermissionDataSource
import com.example.myapplication.recipereader.data.resolver.ContactsResolver

class ContactsRepositoryImpl(
    private val resolver: ContactsResolver,
    private val mockDataSource: ContactsMockDataSource,
    private val permissionDataSource: ContactsPermissionDataSource,
    private val favoritesDataSource: ContactsFavoritesDataSource
) : ContactsRepository {

    override suspend fun loadContacts(): ContactsLoadResult {
        val hasPermission = permissionDataSource.hasReadContactsPermission()
        val realContacts = if (hasPermission) resolver.getContacts() else emptyList()
        val isUsingMock = !hasPermission || realContacts.isEmpty()
        val baseContacts = if (isUsingMock) mockDataSource.getContacts() else realContacts
        val favorites = favoritesDataSource.getFavoriteIds()
        val contacts = baseContacts.map { contact ->
            contact.copy(isFavorite = favorites.contains(contact.id))
        }
        return ContactsLoadResult(
            hasPermission = hasPermission,
            isUsingMock = isUsingMock,
            contacts = contacts
        )
    }

    override suspend fun setFavorite(contactId: String, isFavorite: Boolean) {
        favoritesDataSource.setFavorite(contactId, isFavorite)
    }
}
