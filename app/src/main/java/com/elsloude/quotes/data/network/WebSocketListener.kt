package com.elsloude.quotes.data.network

import com.elsloude.quotes.common.toJsonArray
import com.elsloude.quotes.data.mapToQuoteResponseDto
import com.elsloude.quotes.data.entity.QuotesRequestDto
import com.elsloude.quotes.data.toErrorDto
import com.google.gson.Gson
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebSocketListener(
    private val callback: QuoteWebSocketCallback,
    private val gson: Gson
    ): WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        val quoteRequest = QuotesRequestDto().toJsonArray().toString()
        webSocket.send(quoteRequest)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        val response = text.mapToQuoteResponseDto(gson)
        callback.onQuoteDataReceived(response)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        val responseError = response?.message?.toErrorDto()
        callback.onWebSocketError(responseError)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {}

    companion object {
        const val TAG = "websocketlistener"
    }
}