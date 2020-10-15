package com.example.room.trigger.ui.entries

import androidx.lifecycle.*
import com.example.room.trigger.data.entry.Entry
import com.example.room.trigger.domain.usecase.GetEntriesUseCase
import com.example.room.trigger.domain.usecase.AddEntryUseCase
import com.example.room.trigger.domain.usecase.DeleteEntryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EntriesViewModel(
    private val getEntriesUseCase: GetEntriesUseCase,
    private val addEntryUseCase: AddEntryUseCase,
    private val deleteEntryUseCase: DeleteEntryUseCase
) : ViewModel(), LifecycleObserver {

    private val events = MutableLiveData<EntriesEvents>()
    private val entries = mutableListOf<Entry>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        loadEntries()
    }

    fun getEvents(): LiveData<EntriesEvents> = events

    fun onAddEntryClicked() {
        viewModelScope.launch {
            events.value = EntriesEvents.Loading

            val previousEntry = entries.lastOrNull()
            val result = withContext(Dispatchers.IO) {
                addEntryUseCase(previousEntry)
            }

            entries.add(result)
            events.value = EntriesEvents.AddEntry(result)
        }

    }

    fun onRemoveEntryClicked() {
        val entry = entries.removeLastOrNull() ?: return

        viewModelScope.launch {
            events.value = EntriesEvents.Loading

            withContext(Dispatchers.IO) {
                deleteEntryUseCase(entry)
            }

            events.value = EntriesEvents.RemoveEntry(entry)
        }
    }

    private fun loadEntries() {
        viewModelScope.launch {
            events.value = EntriesEvents.Loading

            val result = withContext(Dispatchers.IO) {
                getEntriesUseCase()
            }

            entries.clear()
            entries.addAll(result)
            events.value = EntriesEvents.EntriesLoaded(entries)
        }
    }
}