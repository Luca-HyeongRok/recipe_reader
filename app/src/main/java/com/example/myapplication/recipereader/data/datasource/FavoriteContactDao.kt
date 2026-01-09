package com.example.myapplication.recipereader.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteContactDao {
    @Query("SELECT contactId FROM favorite_contacts")
    suspend fun getAllIds(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FavoriteContactEntity)

    @Query("DELETE FROM favorite_contacts WHERE contactId = :contactId")
    suspend fun delete(contactId: String)
}
