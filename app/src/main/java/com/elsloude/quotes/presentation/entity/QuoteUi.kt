package com.elsloude.quotes.presentation.entity

import com.elsloude.quotes.domain.entity.QuoteResponse
import java.math.BigDecimal

data class QuoteUi(
    val ticker: String? = null,
    val companyName: String? = null,
    val percentChangeFromPreviousClose: Double? = null,
    val lastTradePrice: Double? = null,
    val lastTradeExchange: String? = null,
    val priceChangeInPoints: BigDecimal? = null,
    val error: String? = null,
    val colorChangesBackground: Int? = null,
    val changePriceColor: Int? = null
)

fun QuoteResponse.toUiModel(): QuoteUi =
    QuoteUi(
        companyName = this.companyName,
        ticker = this.ticker,
        percentChangeFromPreviousClose = this.percentChangeFromPreviousClose,
        lastTradePrice = this.lastTradePrice,
        lastTradeExchange = this.lastTradeExchange,
        priceChangeInPoints = this.priceChangeInPoints?.toBigDecimal(),
        error = this.error
    )

