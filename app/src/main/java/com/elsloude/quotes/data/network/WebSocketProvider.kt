package com.elsloude.quotes.data.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import javax.inject.Inject

class WebSocketProvider @Inject constructor(
    private val client: OkHttpClient
) {

    companion object {
        private const val TRADERNET_URL = "wss://wss.tradernet.ru"
    }

    private var webSocket: WebSocket? = null

    fun startWebSocket(webSocketListener: WebSocketListener) {
        webSocket = client.newWebSocket(
            Request
                .Builder()
                .url(TRADERNET_URL)
                .build(),
            webSocketListener
        )
    }

    fun closeConnection() {
        webSocket = null
    }
}