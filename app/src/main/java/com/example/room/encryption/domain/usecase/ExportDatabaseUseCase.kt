package com.example.room.encryption.domain.usecase

import com.example.room.encryption.data.repository.EncryptedEntriesRepository

class ExportDatabaseUseCase(
    val entriesRepository: EncryptedEntriesRepository
) {

    operator fun invoke(shouldDecrypt: Boolean) {
        entriesRepository.exportDatabase(shouldDecrypt)
    }
}