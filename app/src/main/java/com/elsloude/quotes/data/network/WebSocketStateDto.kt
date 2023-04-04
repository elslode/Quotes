package com.elsloude.quotes.data.network

sealed class WebSocketStateDto {
    class QuoteData(val quoteData: String) : WebSocketStateDto()
    class Error(val messageError: String) : WebSocketStateDto()
}
