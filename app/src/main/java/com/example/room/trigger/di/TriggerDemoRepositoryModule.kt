package com.example.room.trigger.di

import android.content.Context
import com.example.room.trigger.data.TriggerDemoDatabase
import com.example.room.trigger.data.repository.EntriesRepository
import com.example.room.trigger.data.repository.EntriesRepositoryImpl
import org.koin.dsl.module

val triggerDemoRepositoryModule = module {
    single { provideTriggerDemoDatabase(get()) }
    single { provideEntriesRepository(get()) }
}

private fun provideTriggerDemoDatabase(context: Context): TriggerDemoDatabase {
    return TriggerDemoDatabase.create(context)
}

private fun provideEntriesRepository(database: TriggerDemoDatabase): EntriesRepository {
    return EntriesRepositoryImpl(database.entryDao(), database.logDao())
}
