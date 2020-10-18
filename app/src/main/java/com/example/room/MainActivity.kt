package com.example.room

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.room.encryption.ui.EncryptionActivity
import com.example.room.trigger.ui.TriggerDemoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        requestPermissions()
    }

    private fun setupListeners() {
        btn_trigger_demo.setOnClickListener {
            startActivity(TriggerDemoActivity.getIntent(this))
        }
        btn_encryption_demo.setOnClickListener {
            startActivity(EncryptionActivity.getIntent(this))
        }
    }

    // TODO: Maybe refactor permissions mechanism
    private fun requestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
        }
    }
}