package com.example.room.encryption.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.encryption.data.entry.EncryptedEntry
import com.example.room.encryption.data.entry.EncryptedEntryDao
import com.example.room.encryption.utils.DBKeyManager
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@Database(entities = [EncryptedEntry::class], version = 1)
abstract class EncryptedDatabase : RoomDatabase() {

    companion object {
        private val TAG = EncryptedDatabase::class.java.simpleName
        const val DATABASE_NAME = "encrypted_db"

        fun create(context: Context): EncryptedDatabase {
            val dbKey = DBKeyManager.getKeyOrCreate(context)
            val supportFactory = SupportFactory(SQLiteDatabase.getBytes(dbKey.toCharArray()))

            return Room.databaseBuilder(context, EncryptedDatabase::class.java, DATABASE_NAME)
                .openHelperFactory(supportFactory)
                .build()
        }

        fun export(context: Context) {
            val dbFile = context.getDatabasePath(DATABASE_NAME)
            if (dbFile.exists()) {
                val destinationPath = context.getExternalFilesDir(null)?.let {
                    "${it.path}/$DATABASE_NAME.sqlite"
                } ?: return
                val exportedDbFile = File(destinationPath)

                dbFile.copyTo(
                    target = exportedDbFile,
                    overwrite = true,
                    bufferSize = 1024
                )
            }
        }

        fun exportDecrypted(context: Context) {
            val dbFile = context.getDatabasePath(DATABASE_NAME)
            if (dbFile.exists()) {
                val tempDbFile = File.createTempFile(DATABASE_NAME, "tmp", context.cacheDir)

                val dbKey = DBKeyManager.getKeyOrCreate(context)
                val database = SQLiteDatabase.openDatabase(
                    dbFile.absolutePath,
                    dbKey,
                    null,
                    SQLiteDatabase.OPEN_READWRITE
                )
                try {
                    database.rawExecSQL("ATTACH DATABASE '${tempDbFile.absolutePath}' AS plaintext KEY '';")
                    database.rawExecSQL("SELECT sqlcipher_export('plaintext')")
                    database.rawExecSQL("DETACH DATABASE plaintext;")
                } catch (e: Exception) {
                    Log.e(TAG, "Error on exporting database", e)
                } finally {
                    database.close()
                }

                if (tempDbFile.exists()) {
                    val destinationPath = context.getExternalFilesDir(null)?.let {
                        "${it.path}/${DATABASE_NAME}_decrypted.sqlite"
                    } ?: return
                    val exportedDbFile = File(destinationPath)

                    dbFile.copyTo(
                        target = exportedDbFile,
                        overwrite = true,
                        bufferSize = 1024
                    )

                    tempDbFile.delete()
                }
            }
        }
    }

    abstract fun encryptedEntryDao(): EncryptedEntryDao
}