package com.example.room.encryption.ui.entries

import com.example.room.encryption.data.entry.EncryptedEntry

sealed class EncryptedEntriesEvents {

    object Loading : EncryptedEntriesEvents()

    data class EntriesLoaded(val entries: List<EncryptedEntry>) : EncryptedEntriesEvents()

    data class AddEntry(val entry: EncryptedEntry) : EncryptedEntriesEvents()

    data class RemoveEntry(val entry: EncryptedEntry) : EncryptedEntriesEvents()
}