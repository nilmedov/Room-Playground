package com.example.room.trigger.data

import android.content.Context
import android.database.Cursor
import android.os.CancellationSignal
import android.util.Log as log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.room.trigger.data.entry.Entry
import com.example.room.trigger.data.entry.EntryDao
import com.example.room.trigger.data.log.Log
import com.example.room.trigger.data.log.LogDao

@Database(entities = [Entry::class, Log::class], version = 1)
abstract class TriggerDemoDatabase : RoomDatabase() {

    abstract fun entryDao(): EntryDao

    abstract fun logDao(): LogDao

    override fun query(query: String, args: Array<out Any>?): Cursor {
        val queryString = if (args.isNullOrEmpty()) {
            query
        } else {
            String.format(query.replace("?", "%s"), args)
        }
        log.d(TAG, queryString)

        return super.query(query, args)
    }

    override fun query(query: SupportSQLiteQuery, signal: CancellationSignal?): Cursor {
        log.d(TAG, query.sql)

        return super.query(query, signal)
    }

    companion object {
        private val TAG = TriggerDemoDatabase::class.java.simpleName
        private const val DATABASE_NAME = "trigger_demo_db"

        fun create(context: Context): TriggerDemoDatabase {
            return Room.databaseBuilder(context, TriggerDemoDatabase::class.java, DATABASE_NAME)
                .addCallback(TriggerDemoDatabaseCallback())
                .allowMainThreadQueries() // TODO: Add coroutines and remove this
                .build()
        }
    }
}

private class TriggerDemoDatabaseCallback : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TRIGGER log_insert_transaction AFTER INSERT ON entry
            BEGIN
            INSERT INTO log(timestamp, operation, payload) VALUES(datetime(), 'INSERT', ' _id = ' || new._id || ', title = ' || new.title || ', timestamp = ' || new.timestamp);
            END;
            """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TRIGGER log_delete_transaction AFTER DELETE ON entry
            BEGIN
            INSERT INTO log(timestamp, operation, payload) VALUES(datetime(), 'DELETE', ' _id = ' || old._id || ', title = ' || old.title || ', timestamp = ' || old.timestamp);
            END;
            """.trimIndent()
        )
    }
}