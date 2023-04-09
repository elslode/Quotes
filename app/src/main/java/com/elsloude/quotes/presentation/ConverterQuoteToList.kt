package com.elsloude.quotes.presentation

import com.elsloude.quotes.R
import com.elsloude.quotes.presentation.entity.QuoteUi

class ConverterQuoteToList {

    fun convert(
        savedMap: MutableMap<String, QuoteUi>,
        currentValue: QuoteUi
    ): List<QuoteUi> {

        val result = mutableListOf<QuoteUi>()

        if (savedMap[currentValue.ticker] == null && currentValue.ticker?.isNotBlank() == true) {
                savedMap[currentValue.ticker] = currentValue
        }

        savedMap.map { item ->
            val savedValue = savedMap[currentValue.ticker]

            val companyName = currentValue.companyName ?: savedValue?.companyName
            val ticker = currentValue.ticker ?: savedValue?.ticker
            val prevClosePctChange = currentValue.percentChangeFromPreviousClose
                ?: savedValue?.percentChangeFromPreviousClose
            val lastTradePrice = currentValue.lastTradePrice ?: savedValue?.lastTradePrice
            val lastTradeExchange = currentValue.lastTradeExchange ?: savedValue?.lastTradeExchange
            val priceChangeInPoints =
                currentValue.priceChangeInPoints ?: savedValue?.priceChangeInPoints

            val backgroundChange = savedValue?.priceChangeInPoints?.let {
                when {
                    currentValue.priceChangeInPoints != null && it > currentValue.priceChangeInPoints -> R.color.red
                    currentValue.priceChangeInPoints != null && it < currentValue.priceChangeInPoints -> R.color.green
                    else -> null
                }
            }

            val priceChangeTextColor = when {
                backgroundChange != null -> R.color.white
                priceChangeInPoints != null && priceChangeInPoints > 0.0 -> R.color.green
                priceChangeInPoints != null && priceChangeInPoints < 0.0 -> R.color.green
                else -> R.color.black
            }

            val cashedValue = QuoteUi(
                companyName = companyName,
                ticker = ticker,
                percentChangeFromPreviousClose = prevClosePctChange,
                lastTradePrice = lastTradePrice,
                lastTradeExchange = lastTradeExchange,
                priceChangeInPoints = priceChangeInPoints,
                changePriceColor = priceChangeTextColor,
                colorChangesBackground = backgroundChange,
            )

            savedMap[item.key] = cashedValue


            result.add(cashedValue)
        }

        return result
    }
}