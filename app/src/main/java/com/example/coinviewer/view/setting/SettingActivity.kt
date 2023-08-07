package com.example.coinviewer.view.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.coinviewer.R
import com.example.coinviewer.databinding.ActivitySettingBinding
import com.example.coinviewer.service.PriceForegroundService

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.startForeground.setOnClickListener {
            Toast.makeText(this, "start", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, PriceForegroundService::class.java)
            intent.action = "START"
            startService(intent)
        }

        binding.stopForeground.setOnClickListener {
            Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, PriceForegroundService::class.java)
            intent.action = "STOP"
            startService(intent)
        }

    }

}