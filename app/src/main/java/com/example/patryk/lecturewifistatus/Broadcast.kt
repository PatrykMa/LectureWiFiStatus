package com.example.patryk.lecturewifistatus

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Broadcast:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,intent?.action,Toast.LENGTH_LONG ).show()
    }
}