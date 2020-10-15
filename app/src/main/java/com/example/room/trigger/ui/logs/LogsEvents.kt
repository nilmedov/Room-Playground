package com.example.room.trigger.ui.logs

import com.example.room.trigger.data.log.Log

sealed class LogsEvents {

    object Loading : LogsEvents()

    data class LogsLoaded(val logs: List<Log>) : LogsEvents()
}