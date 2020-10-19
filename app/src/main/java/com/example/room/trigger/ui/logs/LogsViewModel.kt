package com.example.room.trigger.ui.logs

import androidx.lifecycle.*
import com.example.room.trigger.domain.usecase.GetLogsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

class LogsViewModel(
    private val getLogsUseCase: GetLogsUseCase
) : ViewModel(), LifecycleObserver {

    private val events = MutableLiveData<LogsEvents>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        loadLogs()
    }

    fun getEvents(): LiveData<LogsEvents> = events

    private fun loadLogs() {
        viewModelScope.launch {
            events.value = LogsEvents.Loading

            getLogsUseCase()
                .flowOn(Dispatchers.IO)
                .collect {
                    events.value = LogsEvents.LogsLoaded(it)
                }
        }

    }
}