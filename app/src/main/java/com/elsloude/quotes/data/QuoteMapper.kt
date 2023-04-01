package com.elsloude.quotes.data

import com.elsloude.quotes.data.model.QuoteDataDto
import com.elsloude.quotes.data.model.QuoteResponseDto

fun QuoteDataDto.mapQuoteDataToResponseDto(): QuoteResponseDto {
    return QuoteResponseDto(
        companyName = this.name,
        ticker = this.c,
        percentChangeFromPreviousClose = this.pcp,
        lastTradePrice = this.ltp,
        lastTradeExchange = this.ltr,
        priceChangeInPoints = this.chg
    )
}
