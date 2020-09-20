package com.example.room.trigger.data.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entry")
data class Entry(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val id: Int,
    val title: String,
    val timestamp: Long
)