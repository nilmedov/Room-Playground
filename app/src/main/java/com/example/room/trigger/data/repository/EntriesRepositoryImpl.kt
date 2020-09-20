package com.example.room.trigger.data.repository

import com.example.room.trigger.data.entry.Entry
import com.example.room.trigger.data.entry.EntryDao
import com.example.room.trigger.data.log.Log
import com.example.room.trigger.data.log.LogDao

class EntriesRepositoryImpl(
    private val entryDao: EntryDao,
    private val logDao: LogDao
) : EntriesRepository {

    override fun getEntries(): List<Entry> {
        return entryDao.getAll()
    }

    override fun storeEntry(entry: Entry) {
        entryDao.insert(entry)
    }

    override fun deleteEntry(entry: Entry) {
        entryDao.delete(entry)
    }

    override fun getLogs(): List<Log> {
        return logDao.getAll()
    }
}