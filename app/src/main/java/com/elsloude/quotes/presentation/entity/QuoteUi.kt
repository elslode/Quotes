package com.elsloude.quotes.presentation.entity

import com.elsloude.quotes.domain.entity.QuoteResponse

data class QuoteUi(
    val companyName: String? = null,
    val ticker: String? = null,
    val percentChangeFromPreviousClose: Double? = null,
    val lastTradePrice: Double? = null,
    val lastTradeExchange: String? = null,
    val priceChangeInPoints: Double? = null,
    val error: String? = null
)

fun QuoteResponse.toUiModel(): QuoteUi =
    QuoteUi(
        companyName = this.companyName,
        ticker = this.ticker,
        percentChangeFromPreviousClose = this.percentChangeFromPreviousClose,
        lastTradePrice = this.lastTradePrice,
        lastTradeExchange = this.lastTradeExchange,
        priceChangeInPoints = this.priceChangeInPoints,
        error = this.error
    )

