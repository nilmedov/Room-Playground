package com.example.room.trigger.data.log

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {

    @Query("SELECT * FROM log")
    fun getAll(): Flow<List<Log>>
}