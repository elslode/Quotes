package com.elsloude.quotes.data.network

import com.elsloude.quotes.data.entity.QuoteDto

interface QuoteWebSocketCallback {
    fun onQuoteDataReceived(quoteDataDto: QuoteDto?)
    fun onWebSocketError(error: QuoteDto?)
}