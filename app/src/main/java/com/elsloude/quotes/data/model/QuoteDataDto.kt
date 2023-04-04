package com.elsloude.quotes.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteDataDto(
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
