package com.example.myapplication.recipereader.data.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_contacts")
data class FavoriteContactEntity(
    @PrimaryKey val contactId: String
)
