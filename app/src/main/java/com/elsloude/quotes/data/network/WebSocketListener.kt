package com.elsloude.quotes.data.network

import android.util.Log
import com.elsloude.quotes.common.toJsonArray
import com.elsloude.quotes.data.model.QuotesRequestDto
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebSocketListener(
    private val webSocketContract: QuoteWebSocketContracts
) : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)

        val quoteRequest = QuotesRequestDto().toJsonArray().toString()
        webSocket.send(quoteRequest)
        Log.d(TAG, "onOpen: $quoteRequest")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        webSocketContract.setQuote(WebSocketStateDto.QuoteData(text))
        Log.d(TAG, "onMessage: $text")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        webSocketContract.setQuote(WebSocketStateDto.Error(t.message.toString()))
        Log.d(TAG, "onFailure: ${t.localizedMessage} ${t.message}")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        Log.d(TAG, "onClosing: $code $reason")
    }

    companion object {
        const val TAG = "websocketlistener"
    }
}