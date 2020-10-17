package com.example.room.encryption.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.encryption.data.entry.EncryptedEntry
import com.example.room.encryption.data.entry.EncryptedEntryDao
import com.example.room.encryption.utils.DBKeyManager
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [EncryptedEntry::class], version = 1)
abstract class EncryptedDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "encrypted_db"

        fun create(context: Context): EncryptedDatabase {
            val dbKey = DBKeyManager.getKeyOrCreate(context)
            val supportFactory = SupportFactory(SQLiteDatabase.getBytes(dbKey.toCharArray()))

            return Room.databaseBuilder(context, EncryptedDatabase::class.java, DATABASE_NAME)
                .openHelperFactory(supportFactory)
                .build()
        }
    }

    abstract fun encryptedEntryDao(): EncryptedEntryDao
}