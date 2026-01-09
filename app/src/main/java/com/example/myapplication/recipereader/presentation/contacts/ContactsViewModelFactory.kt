package com.example.myapplication.recipereader.presentation.contacts

import android.content.ContentResolver
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.recipereader.data.datasource.ContactsDatabase
import com.example.myapplication.recipereader.data.datasource.ContactsFavoritesDataSource
import com.example.myapplication.recipereader.data.datasource.ContactsMockDataSource
import com.example.myapplication.recipereader.data.datasource.ContactsPermissionDataSource
import com.example.myapplication.recipereader.data.repository.ContactsRepositoryImpl
import com.example.myapplication.recipereader.data.resolver.ContactsResolver

class ContactsViewModelFactory(
    private val context: Context,
    private val contentResolver: ContentResolver
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val resolver = ContactsResolver(contentResolver)
        val mockDataSource = ContactsMockDataSource()
        val permissionDataSource = ContactsPermissionDataSource(context)
        val database = ContactsDatabase.getInstance(context)
        val favoritesDataSource = ContactsFavoritesDataSource(database.favoriteContactDao())
        val repository = ContactsRepositoryImpl(
            resolver = resolver,
            mockDataSource = mockDataSource,
            permissionDataSource = permissionDataSource,
            favoritesDataSource = favoritesDataSource
        )

        @Suppress("UNCHECKED_CAST")
        return ContactsViewModel(repository) as T
    }
}
