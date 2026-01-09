package com.example.myapplication.recipereader.data.resolver

import android.content.ContentResolver
import android.provider.ContactsContract
import com.example.myapplication.recipereader.domain.model.Contact

class ContactsResolver(
    private val contentResolver: ContentResolver
) {
    fun getContacts(): List<Contact> {
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        val cursor = try {
            contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection,
                null,
                null,
                "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY} ASC"
            )
        } catch (e: Exception) {
            null
        } ?: return emptyList()

        val contacts = LinkedHashMap<String, Contact>()
        cursor.use {
            val idIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY)
            val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            if (idIndex == -1 || nameIndex == -1 || numberIndex == -1) return emptyList()

            while (it.moveToNext()) {
                val id = it.getString(idIndex)?.trim().orEmpty()
                val name = it.getString(nameIndex)?.trim().orEmpty()
                val number = it.getString(numberIndex)?.trim().orEmpty()
                if (id.isEmpty() || name.isEmpty() || number.isEmpty()) continue

                if (!contacts.containsKey(id)) {
                    contacts[id] = Contact(
                        id = id,
                        name = name,
                        phoneNumber = number,
                        isFavorite = false
                    )
                }
            }
        }
        return contacts.values.toList()
    }
}
