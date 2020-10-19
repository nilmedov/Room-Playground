package com.example.room.trigger.domain.usecase

import com.example.room.trigger.data.log.Log
import com.example.room.trigger.data.repository.EntriesRepository
import kotlinx.coroutines.flow.Flow

class GetLogsUseCase(
    private val entriesRepository: EntriesRepository
) {

    operator fun invoke(): Flow<List<Log>> = entriesRepository.getLogs()
}