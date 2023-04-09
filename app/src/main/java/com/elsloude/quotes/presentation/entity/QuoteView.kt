package com.elsloude.quotes.presentation.entity

data class QuoteView(
    val tickerName: String,
    val traderName: String,
    val lastPrice: String,
    val quoteChange: String,
    val changePriceColor: Int?,
    val changePriceBackground: Int?
)

fun QuoteUi.toQuoteView() =
    QuoteView(
        tickerName = "$ticker",
        traderName = "$lastTradeExchange | $companyName",
        lastPrice = "$lastTradePrice ($percentChangeFromPreviousClose)",
        quoteChange = "$priceChangeInPoints%",
        changePriceBackground = colorChangesBackground,
        changePriceColor = changePriceColor
    )
