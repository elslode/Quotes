package com.elsloude.quotes.data.network

import android.util.Log
import com.elsloude.quotes.common.toJsonArray
import com.elsloude.quotes.data.mapQuoteDataToResponseDto
import com.elsloude.quotes.data.model.QuoteDataDto
import com.elsloude.quotes.data.model.QuotesRequestDto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject


class WebSocketListener: WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)

        val quoteRequest = QuotesRequestDto().toJsonArray().toString()
        webSocket.send(quoteRequest)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)

        val jsonArray = Json.decodeFromString<JsonArray>(text)
        val quoteJsonObject = jsonArray[1] as JsonObject
        val quoteData = Json.decodeFromJsonElement<QuoteDataDto>(quoteJsonObject)
        val quoteResponseDto = quoteData.mapQuoteDataToResponseDto()

        Log.d(TAG, "onMessage: $quoteResponseDto")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        Log.d(TAG, "onFailure: ${t.localizedMessage}")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        Log.d(TAG, "onClosing: $code")
        Log.d(TAG, "onClosing: $reason")
    }

    companion object {
        const val TAG = "websocketlistener"
    }
}