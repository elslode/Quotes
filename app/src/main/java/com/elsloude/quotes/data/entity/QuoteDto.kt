package com.elsloude.quotes.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteDto(
    @SerializedName("c")
    val ticker: String? = null,
    @SerializedName("name")
    val companyName: String? = null,
    @SerializedName("pcp")
    val percentChangeFromPreviousClose: Double? = null,
    @SerializedName("ltp")
    val lastTradePrice: Double? = null,
    @SerializedName("ltr")
    val lastTradeExchange: String? = null,
    @SerializedName("chg")
    val priceChangeInPoints: Double? = null,
    val error: String? = null
)
