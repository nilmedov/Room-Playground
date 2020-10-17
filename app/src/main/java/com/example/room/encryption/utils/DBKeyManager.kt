package com.example.room.encryption.utils

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.security.SecureRandom

object DBKeyManager {

    private const val PREFS_NAME = "encrypted_prefs"
    private const val DB_KEY_PREFS_ALIAS = "DB_KEY_PREFS_ALIAS"

    private val HEX_CHARS = "0123456789ABCDEF".toCharArray()

    fun getKeyOrCreate(context: Context): String {
        val sharedPrefs = getEncryptedSharedPreferences(context)
        if (!sharedPrefs.contains(DB_KEY_PREFS_ALIAS)) {
            val dbKey = createDBKey()
            sharedPrefs.edit()
                .putString(DB_KEY_PREFS_ALIAS, dbKey)
                .apply()

            return dbKey
        }

        return sharedPrefs.getString(DB_KEY_PREFS_ALIAS, null)!!
    }

    private fun getEncryptedSharedPreferences(context: Context): SharedPreferences {
        // Creates or gets the key to encrypt and decrypt.
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        return EncryptedSharedPreferences.create(
            PREFS_NAME,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun createDBKey(): String {
        // This is the raw key that we'll be encrypting + storing
        val rawByteKey = generateRandomKey()
        // This is the key that will be used by Room
        val dbCharKey = rawByteKey.toHex()

        return dbCharKey
    }

    /**
     * Generates a random 32 byte key.
     *
     * @return a byte array containing random values
     */
    private fun generateRandomKey(): ByteArray =
        ByteArray(32).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                SecureRandom.getInstanceStrong().nextBytes(this)
            } else {
                SecureRandom().nextBytes(this)
            }
        }

    /**
     * Extension function that converts a ByteArray to a hex encoded String
     */
    private fun ByteArray.toHex(): String {
        val result = StringBuilder()
        forEach {
            val octet = it.toInt()
            val firstIndex = (octet and 0xF0).ushr(4)
            val secondIndex = octet and 0x0F
            result.append(HEX_CHARS[firstIndex])
            result.append(HEX_CHARS[secondIndex])
        }
        return result.toString()
    }
}