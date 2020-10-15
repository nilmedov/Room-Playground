package com.example.room.trigger.di

import com.example.room.trigger.ui.entries.EntriesViewModel
import com.example.room.trigger.ui.logs.LogsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val triggerDemoViewModelModule = module {
    viewModel { EntriesViewModel(
        getEntriesUseCase = get(),
        addEntryUseCase = get(),
        deleteEntryUseCase = get())
    }
    viewModel { LogsViewModel(get()) }
}