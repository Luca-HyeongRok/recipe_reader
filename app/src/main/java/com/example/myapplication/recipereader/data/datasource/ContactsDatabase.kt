package com.example.myapplication.recipereader.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteContactEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ContactsDatabase : RoomDatabase() {
    abstract fun favoriteContactDao(): FavoriteContactDao

    companion object {
        @Volatile
        private var instance: ContactsDatabase? = null

        fun getInstance(context: Context): ContactsDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    ContactsDatabase::class.java,
                    "contacts.db"
                ).build().also { instance = it }
            }
        }
    }
}
