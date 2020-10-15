package com.example.room.trigger.domain.usecase

import com.example.room.trigger.data.entry.Entry
import com.example.room.trigger.data.repository.EntriesRepository

class AddEntryUseCase(
    private val entriesRepository: EntriesRepository
) {

    operator fun invoke(previousEntry: Entry?): Entry {
        val entry = createNextEntry(previousEntry)
        entriesRepository.storeEntry(entry)

        return entry
    }

    private fun createNextEntry(previousEntry: Entry?): Entry {
        return if (previousEntry == null) {
            Entry(id = 1, title = "Entry 1", timestamp = System.currentTimeMillis())
        } else {
            val id = previousEntry.id + 1
            Entry(id = id, title = "Entry $id", timestamp = System.currentTimeMillis())
        }
    }
}