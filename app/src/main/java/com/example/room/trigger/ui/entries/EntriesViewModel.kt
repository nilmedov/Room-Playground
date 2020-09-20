package com.example.room.trigger.ui.entries

import androidx.lifecycle.*
import com.example.room.trigger.data.entry.Entry
import com.example.room.trigger.data.repository.EntriesRepository

class EntriesViewModel(
    private val entriesRepository: EntriesRepository
) : ViewModel(), LifecycleObserver {

    private val events = MutableLiveData<EntriesEvents>()
    private val entries = mutableListOf<Entry>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        loadEntries()
    }

    fun getEvents(): LiveData<EntriesEvents> = events

    fun onAddEntryClicked() {
        val entry = createNextEntry(previousEntry = entries.lastOrNull())
        entries.add(entry)
        entriesRepository.storeEntry(entry)
        events.value = EntriesEvents.AddEntry(entry)
    }

    fun onRemoveEntryClicked() {
        val entry = entries.removeLastOrNull() ?: return
        entriesRepository.deleteEntry(entry)
        events.value = EntriesEvents.RemoveEntry(entry)
    }

    private fun loadEntries() {
        entries.clear()
        entries.addAll(entriesRepository.getEntries())
        events.value = EntriesEvents.EntriesLoaded(entries)
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