package com.example.room.encryption.data.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "encrypted_entry")
class EncryptedEntry(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val id: Int,
    val title: String,
    val timestamp: Long
)