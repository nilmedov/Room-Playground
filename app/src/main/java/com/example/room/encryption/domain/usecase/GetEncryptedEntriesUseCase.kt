package com.example.room.encryption.domain.usecase

import com.example.room.encryption.data.entry.EncryptedEntry
import com.example.room.encryption.data.repository.EncryptedEntriesRepository

class GetEncryptedEntriesUseCase(
    private val entriesRepository: EncryptedEntriesRepository
) {

    operator fun invoke(): List<EncryptedEntry> = entriesRepository.getEntries()
}