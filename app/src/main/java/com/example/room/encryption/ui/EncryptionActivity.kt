package com.example.room.encryption.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room.R
import com.example.room.encryption.ui.entries.EncryptedEntriesFragment

class EncryptionActivity : AppCompatActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, EncryptionActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encryption)

        setupFragment()
    }

    private fun setupFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, EncryptedEntriesFragment())
            .commit()
    }
}