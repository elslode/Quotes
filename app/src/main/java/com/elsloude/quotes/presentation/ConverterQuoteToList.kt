package com.elsloude.quotes.presentation

import com.elsloude.quotes.R
import com.elsloude.quotes.common.toList
import com.elsloude.quotes.presentation.entity.QuoteUi
import java.math.BigDecimal

class ConverterQuoteToList {

    fun convert(
        savedMap: HashMap<String, QuoteUi>,
        currentMap: HashMap<String, QuoteUi>,
    ): List<QuoteUi> {

        var result = hashMapOf<String, QuoteUi>()
        currentMap.forEach { item ->
            val savedValue = savedMap[item.key]
            val currentValue = item.value

            val companyName = currentValue.companyName ?: savedValue?.companyName
            val ticker = currentValue.ticker ?: savedValue?.ticker
            val prevClosePctChange = currentValue.percentChangeFromPreviousClose
                ?: savedValue?.percentChangeFromPreviousClose ?: 0.0
            val lastTradePrice = currentValue.lastTradePrice ?: savedValue?.lastTradePrice  ?: 0.0
            val lastTradeExchange = currentValue.lastTradeExchange ?: savedValue?.lastTradeExchange
            val priceChangeInPoints =
                currentValue.priceChangeInPoints ?: savedValue?.priceChangeInPoints ?: BigDecimal(0)

            val backgroundChange = savedValue?.priceChangeInPoints?.let {
                when {
                    currentValue.priceChangeInPoints != null && it > currentValue.priceChangeInPoints -> R.drawable.bg_red
                    currentValue.priceChangeInPoints != null && it < currentValue.priceChangeInPoints -> R.drawable.bg_green
                    else -> null
                }
            }

            val priceChangeTextColor = when {
                backgroundChange != null -> R.color.white
                priceChangeInPoints != null && priceChangeInPoints > BigDecimal(0) -> R.color.green
                priceChangeInPoints != null && priceChangeInPoints < BigDecimal(0) -> R.color.red
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

            result = savedMap
        }

        return result.toList()
    }
}