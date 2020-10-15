package com.example.room.trigger.domain.usecase

import com.example.room.trigger.data.log.Log
import com.example.room.trigger.data.repository.EntriesRepository

class GetLogsUseCase(
    private val entriesRepository: EntriesRepository
) {

    operator fun invoke(): List<Log> = entriesRepository.getLogs()
}