package com.example.room.encryption.data.repository

import android.content.Context
import com.example.room.encryption.data.EncryptedDatabase
import com.example.room.encryption.data.entry.EncryptedEntry
import com.example.room.encryption.data.entry.EncryptedEntryDao

class EncryptedEntriesRepositoryImpl(
    private val context: Context,
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

    override fun exportDatabase(shouldDecrypt: Boolean) {
        if (shouldDecrypt) {
            EncryptedDatabase.exportDecrypted(context)
        } else {
            EncryptedDatabase.export(context)
        }
    }
}