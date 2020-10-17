package com.example.room.encryption.data.repository

import com.example.room.encryption.data.entry.EncryptedEntry

interface EncryptedEntriesRepository {

    fun getEntries(): List<EncryptedEntry>

    fun storeEntry(entry: EncryptedEntry)

    fun deleteEntry(entry: EncryptedEntry)
}