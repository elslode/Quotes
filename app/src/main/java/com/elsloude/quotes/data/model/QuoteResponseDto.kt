package com.elsloude.quotes.data.model

import kotlinx.serialization.SerialName

data class QuoteResponseDto(
    @SerialName("name")
    val companyName: String,
    @SerialName("c")
    val ticker: String,
    @SerialName("pcp")
    val percentChangeFromPreviousClose: Double,
    @SerialName("ltp")
    val lastTradePrice: Double,
    @SerialName("ltr")
    val lastTradeExchange: String,
    @SerialName("chg")
    val priceChangeInPoints: Double
)
