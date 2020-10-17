package com.example.room.encryption.domain.usecase

import com.example.room.encryption.data.entry.EncryptedEntry
import com.example.room.encryption.data.repository.EncryptedEntriesRepository

class AddEncryptedEntryUseCase(
    private val entriesRepository: EncryptedEntriesRepository
) {

    operator fun invoke(previousEntry: EncryptedEntry?): EncryptedEntry {
        val entry = createNextEntry(previousEntry)
        entriesRepository.storeEntry(entry)

        return entry
    }

    private fun createNextEntry(previousEntry: EncryptedEntry?): EncryptedEntry {
        return if (previousEntry == null) {
            EncryptedEntry(id = 1, title = "Entry 1", timestamp = System.currentTimeMillis())
        } else {
            val id = previousEntry.id + 1
            EncryptedEntry(id = id, title = "Entry $id", timestamp = System.currentTimeMillis())
        }
    }
}