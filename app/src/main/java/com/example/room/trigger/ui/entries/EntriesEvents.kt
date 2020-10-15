package com.example.room.trigger.ui.entries

import com.example.room.trigger.data.entry.Entry

sealed class EntriesEvents {

    object Loading : EntriesEvents()

    data class EntriesLoaded(val entries: List<Entry>) : EntriesEvents()

    data class AddEntry(val entry: Entry) : EntriesEvents()

    data class RemoveEntry(val entry: Entry) : EntriesEvents()
}