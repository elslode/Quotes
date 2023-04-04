package com.elsloude.quotes.data.network

import kotlinx.coroutines.flow.Flow

interface QuoteWebSocketContracts {
    val quoteStateFlow: Flow<WebSocketStateDto>
    fun setQuote(response: WebSocketStateDto)
}