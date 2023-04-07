package com.elsloude.quotes.data.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class WebSocketProvider(
    private val webSocketCallback: QuoteWebSocketCallback,
    private val gson: Gson
) {

    companion object {
        private const val TRADERNET_URL = "wss://wss.tradernet.ru"
    }

    private var webSocket: WebSocket? = null

    private val client = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .hostnameVerifier { _, _ -> true }
        .retryOnConnectionFailure(true)
        .build()

    fun startWebSocket() {
        webSocket = client.newWebSocket(
            Request
                .Builder()
                .url(TRADERNET_URL)
                .build(),
            WebSocketListener(webSocketCallback, gson)
        )
    }

    fun closeConnection() {
        webSocket = null
    }
}