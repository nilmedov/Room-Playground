package com.example.room.encryption.data.repository

import com.example.room.encryption.data.entry.EncryptedEntry
import com.example.room.encryption.data.entry.EncryptedEntryDao

class EncryptedEntriesRepositoryImpl(
    private val entryDao: EncryptedEntryDao
) : EncryptedEntriesRepository {

    override fun getEntries(): List<EncryptedEntry> {
        return entryDao.getAll()
    }

    override fun storeEntry(entry: EncryptedEntry) {
        entryDao.insert(entry)
    }

    override fun deleteEntry(entry: EncryptedEntry) {
        entryDao.delete(entry)
    }
}