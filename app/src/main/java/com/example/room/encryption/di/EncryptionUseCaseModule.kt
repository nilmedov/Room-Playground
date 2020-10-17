package com.example.room.encryption.di

import com.example.room.encryption.data.repository.EncryptedEntriesRepository
import com.example.room.encryption.domain.usecase.AddEncryptedEntryUseCase
import com.example.room.encryption.domain.usecase.DeleteEncryptedEntryUseCase
import com.example.room.encryption.domain.usecase.GetEncryptedEntriesUseCase
import org.koin.dsl.module

val encryptionUseCaseModule = module {
    factory { provideGetEncryptedEntriesUseCase(get()) }
    factory { provideAddEncryptedEntryUseCase(get()) }
    factory { provideDeleteEncryptedEntryUseCase(get()) }
}

private fun provideGetEncryptedEntriesUseCase(entriesRepository: EncryptedEntriesRepository): GetEncryptedEntriesUseCase {
    return GetEncryptedEntriesUseCase(entriesRepository)
}

private fun provideAddEncryptedEntryUseCase(entriesRepository: EncryptedEntriesRepository): AddEncryptedEntryUseCase {
    return AddEncryptedEntryUseCase(entriesRepository)
}

private fun provideDeleteEncryptedEntryUseCase(entriesRepository: EncryptedEntriesRepository): DeleteEncryptedEntryUseCase {
    return DeleteEncryptedEntryUseCase(entriesRepository)
}