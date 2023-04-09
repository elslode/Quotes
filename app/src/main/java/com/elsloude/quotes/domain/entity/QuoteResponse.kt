package com.elsloude.quotes.domain.entity

data class QuoteResponse(
    val companyName: String?,
    val ticker: String?,
    val percentChangeFromPreviousClose: Double?,
    val lastTradePrice: Double?,
    val lastTradeExchange: String?,
    val priceChangeInPoints: Double?,
    val error: String? = null
)
