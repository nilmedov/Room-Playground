package com.example.room.trigger.data.log

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LogDao {

    @Query("SELECT * FROM log")
    fun getAll(): List<Log>
}