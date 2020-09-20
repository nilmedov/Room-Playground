package com.example.room.trigger.ui.logs

import androidx.lifecycle.*
import com.example.room.trigger.data.repository.EntriesRepository

class LogsViewModel(
    private val entriesRepository: EntriesRepository
) : ViewModel(), LifecycleObserver {

    private val events = MutableLiveData<LogsEvents>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        loadLogs()
    }

    fun getEvents(): LiveData<LogsEvents> = events

    private fun loadLogs() {
        val logs = entriesRepository.getLogs()
        events.value = LogsEvents.LogsLoaded(logs)
    }
}