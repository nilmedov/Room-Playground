package com.example.room.trigger.domain.usecase

import com.example.room.trigger.data.entry.Entry
import com.example.room.trigger.data.repository.EntriesRepository

class DeleteEntryUseCase(
    private val entriesRepository: EntriesRepository
) {

    operator fun invoke(entry: Entry) {
        entriesRepository.deleteEntry(entry)
    }
}