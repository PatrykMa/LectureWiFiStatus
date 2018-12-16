package com.example.patryk.lecturewifistatus

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.lang.Exception
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager



class MainActivity : AppCompatActivity() {

    var isConnected:Boolean=false
        set(value) {findViewById<TextView>(R.id.textView_isConnected).text="jest połączone: $value"}
    var type:String=""
        set(value) {findViewById<TextView>(R.id.textView_connecionType).text=value}
    var SSID:String=""
        set(value) {findViewById<TextView>(R.id.textView_SSID).text=value}

    lateinit var broadcast:Broadcast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try{

            //broadcast
            var filter=IntentFilter()
            broadcast= Broadcast()
            filter.addAction("android.net.wifi.WIFI_STATE_CHANGED")
            filter.addAction("android.net.wifi.STATE_CHANGE")
            registerReceiver(broadcast, filter)

            //
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo: NetworkInfo? = cm.activeNetworkInfo
             isConnected = activeNetworkInfo?.isConnected == true

            //check connection type old
            //activeNetwork?.type
            //check connection type new
            var activeNetwork= cm.activeNetwork
            val capabilities= cm.getNetworkCapabilities(activeNetwork)
            if(capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                type="Wifi"
            else type="nie wifi"

            // SSID
            val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val info = wifiManager.connectionInfo
             SSID = info.ssid
        }
        catch (e:Exception){
            var x=0
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcast)
    }
}
