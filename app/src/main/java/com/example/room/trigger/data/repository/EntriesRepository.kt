package com.example.room.trigger.data.repository

import com.example.room.trigger.data.entry.Entry
import com.example.room.trigger.data.log.Log
import kotlinx.coroutines.flow.Flow

interface EntriesRepository {

    fun getEntries(): List<Entry>

    fun storeEntry(entry: Entry)

    fun deleteEntry(entry: Entry)

    fun getLogs(): Flow<List<Log>>
}