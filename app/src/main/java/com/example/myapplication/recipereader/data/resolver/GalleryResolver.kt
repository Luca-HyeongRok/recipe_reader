package com.example.myapplication.recipereader.data.resolver

import android.content.ContentResolver

class GalleryResolver(
    private val contentResolver: ContentResolver
) {
    // TODO: Query MediaStore for gallery items.
    fun getGalleryItems(): List<String> = emptyList()
}
