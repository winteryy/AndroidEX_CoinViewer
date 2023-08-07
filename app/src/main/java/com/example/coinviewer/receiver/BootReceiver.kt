package com.example.coinviewer.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.coinviewer.service.PriceForegroundService
import timber.log.Timber

class BootReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        Timber.d(intent?.action)

        if(intent?.action.equals("android.intent.action.BOOT_COMPLETED")) {
            val foreground = Intent(context, PriceForegroundService::class.java)
            foreground.action = "START"

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                context?.startForegroundService(foreground)
            }else{
                context?.startService(foreground)
            }
        }
    }
}