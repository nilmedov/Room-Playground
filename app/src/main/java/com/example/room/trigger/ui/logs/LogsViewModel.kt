package com.example.room.trigger.ui.logs

import androidx.lifecycle.*
import com.example.room.trigger.domain.usecase.GetLogsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogsViewModel(
    private val getLogsUseCase: GetLogsUseCase
) : ViewModel(), LifecycleObserver {

    private val events = MutableLiveData<LogsEvents>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        loadLogs()
    }

    fun getEvents(): LiveData<LogsEvents> = events

    private fun loadLogs() {
        viewModelScope.launch {
            events.value = LogsEvents.Loading

            val result = withContext(Dispatchers.IO) {
                getLogsUseCase()
            }

            events.value = LogsEvents.LogsLoaded(result)
        }

    }
}