package com.example.room.encryption.di

import android.content.Context
import com.example.room.encryption.data.EncryptedDatabase
import com.example.room.encryption.data.repository.EncryptedEntriesRepository
import com.example.room.encryption.data.repository.EncryptedEntriesRepositoryImpl
import org.koin.dsl.module

val encryptionRepositoryModule = module {
    single { provideEncryptedDatabase(get()) }
    single { provideEncryptedEntriesRepository(get()) }
}

private fun provideEncryptedDatabase(context: Context): EncryptedDatabase {
    return EncryptedDatabase.create(context)
}

private fun provideEncryptedEntriesRepository(database: EncryptedDatabase): EncryptedEntriesRepository {
    return EncryptedEntriesRepositoryImpl(database.encryptedEntryDao())
}