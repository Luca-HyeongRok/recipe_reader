package com.example.myapplication.recipereader.data.resolver

import android.content.ContentResolver

class ContactsResolver(
    private val contentResolver: ContentResolver
) {
    // TODO: Query ContactsProvider for contacts list.
    fun getContacts(): List<String> = emptyList()
}
