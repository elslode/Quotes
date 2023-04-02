package com.elsloude.quotes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elsloude.quotes.R
import com.elsloude.quotes.data.network.WebSocketProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WebSocketProvider().startWebSocket()
    }
}