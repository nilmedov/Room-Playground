package com.example.room.encryption.di

import com.example.room.encryption.ui.entries.EncryptedEntriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val encryptedViewModelModule = module {
    viewModel {
        EncryptedEntriesViewModel(
            getEntriesUseCase = get(),
            addEntryUseCase = get(),
            deleteEntryUseCase = get(),
            exportDatabaseUseCase = get()
        )
    }
}