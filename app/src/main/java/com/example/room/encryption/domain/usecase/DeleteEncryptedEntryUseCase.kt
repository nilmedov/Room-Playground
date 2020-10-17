package com.example.room.encryption.domain.usecase

import com.example.room.encryption.data.entry.EncryptedEntry
import com.example.room.encryption.data.repository.EncryptedEntriesRepository

class DeleteEncryptedEntryUseCase(
    private val entriesRepository: EncryptedEntriesRepository
) {

    operator fun invoke(entry: EncryptedEntry) {
        entriesRepository.deleteEntry(entry)
    }
}