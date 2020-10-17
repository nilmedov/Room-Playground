package com.example.room.encryption.ui.entries

import androidx.lifecycle.*
import com.example.room.encryption.data.entry.EncryptedEntry
import com.example.room.encryption.domain.usecase.AddEncryptedEntryUseCase
import com.example.room.encryption.domain.usecase.DeleteEncryptedEntryUseCase
import com.example.room.encryption.domain.usecase.GetEncryptedEntriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EncryptedEntriesViewModel(
    private val getEntriesUseCase: GetEncryptedEntriesUseCase,
    private val addEntryUseCase: AddEncryptedEntryUseCase,
    private val deleteEntryUseCase: DeleteEncryptedEntryUseCase
) : ViewModel(), LifecycleObserver {

    private val events = MutableLiveData<EncryptedEntriesEvents>()
    private val entries = mutableListOf<EncryptedEntry>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        loadEntries()
    }

    fun getEvents(): LiveData<EncryptedEntriesEvents> = events

    fun onAddEntryClicked() {
        viewModelScope.launch {
            events.value = EncryptedEntriesEvents.Loading

            val previousEntry = entries.lastOrNull()
            val result = withContext(Dispatchers.IO) {
                addEntryUseCase(previousEntry)
            }

            entries.add(result)
            events.value = EncryptedEntriesEvents.AddEntry(result)
        }

    }

    fun onRemoveEntryClicked() {
        val entry = entries.removeLastOrNull() ?: return

        viewModelScope.launch {
            events.value = EncryptedEntriesEvents.Loading

            withContext(Dispatchers.IO) {
                deleteEntryUseCase(entry)
            }

            events.value = EncryptedEntriesEvents.RemoveEntry(entry)
        }
    }

    private fun loadEntries() {
        viewModelScope.launch {
            events.value = EncryptedEntriesEvents.Loading

            val result = withContext(Dispatchers.IO) {
                getEntriesUseCase()
            }

            entries.clear()
            entries.addAll(result)
            events.value = EncryptedEntriesEvents.EntriesLoaded(entries)
        }
    }
}