package com.example.room.trigger.data.log

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "log")
class Log(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val id: Int,
    val timestamp: String,
    val operation: String,
    val payload: String
)