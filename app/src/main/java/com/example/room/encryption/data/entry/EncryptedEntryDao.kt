package com.example.room.encryption.data.entry

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EncryptedEntryDao {

    @Query("SELECT * FROM encrypted_entry")
    fun getAll(): List<EncryptedEntry>

    @Insert
    fun insert(entry: EncryptedEntry)

    @Delete
    fun delete(entry: EncryptedEntry)
}