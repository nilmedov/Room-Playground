package com.example.room.trigger.data.entry

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EntryDao {

    @Query("SELECT * FROM entry")
    fun getAll(): List<Entry>

    @Insert
    fun insert(entry: Entry)

    @Delete
    fun delete(entry: Entry)
}