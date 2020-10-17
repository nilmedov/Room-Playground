package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room.encryption.ui.EncryptionActivity
import com.example.room.trigger.ui.TriggerDemoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListeners()
    }

    private fun setupListeners() {
        btn_trigger_demo.setOnClickListener {
            startActivity(TriggerDemoActivity.getIntent(this))
        }
        btn_encryption_demo.setOnClickListener {
            startActivity(EncryptionActivity.getIntent(this))
        }
    }
}