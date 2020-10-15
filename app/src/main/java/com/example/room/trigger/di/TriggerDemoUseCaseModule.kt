package com.example.room.trigger.di

import com.example.room.trigger.data.repository.EntriesRepository
import com.example.room.trigger.domain.usecase.AddEntryUseCase
import com.example.room.trigger.domain.usecase.DeleteEntryUseCase
import com.example.room.trigger.domain.usecase.GetEntriesUseCase
import com.example.room.trigger.domain.usecase.GetLogsUseCase
import org.koin.dsl.module

val triggerDemoUseCaseModule = module {
    factory { provideGetEntriesUseCase(get()) }
    factory { provideAddEntryUseCase(get()) }
    factory { provideDeleteEntryUseCase(get()) }
    factory { provideGetLogsUseCase(get()) }
}

private fun provideGetEntriesUseCase(entriesRepository: EntriesRepository): GetEntriesUseCase {
    return GetEntriesUseCase(entriesRepository)
}

private fun provideAddEntryUseCase(entriesRepository: EntriesRepository): AddEntryUseCase {
    return AddEntryUseCase(entriesRepository)
}

private fun provideDeleteEntryUseCase(entriesRepository: EntriesRepository): DeleteEntryUseCase {
    return DeleteEntryUseCase(entriesRepository)
}

private fun provideGetLogsUseCase(entriesRepository: EntriesRepository): GetLogsUseCase {
    return GetLogsUseCase(entriesRepository)
}