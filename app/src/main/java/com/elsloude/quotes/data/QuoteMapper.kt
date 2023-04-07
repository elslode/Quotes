package com.elsloude.quotes.data

import com.elsloude.quotes.data.entity.QuoteDto
import com.elsloude.quotes.domain.entity.QuoteResponse
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser

fun QuoteDto?.toDomainModel(): QuoteResponse {
    return QuoteResponse(
        companyName = this?.companyName.orEmpty(),
        ticker = this?.ticker.orEmpty(),
        percentChangeFromPreviousClose = this?.percentChangeFromPreviousClose ?: 0.0,
        lastTradePrice = this?.lastTradePrice ?: 0.0,
        lastTradeExchange = this?.lastTradeExchange.orEmpty(),
        priceChangeInPoints = this?.priceChangeInPoints ?: 0.0
    )
}

fun String.mapToQuoteResponseDto(gson: Gson): QuoteDto? {

    val jsonElement = JsonParser.parseString(this)

    if (jsonElement is JsonArray && jsonElement.size() >= 2) {
        val messageType = jsonElement.first().asString
        if (messageType == "q") {
            val jsonArray = gson.fromJson(jsonElement, JsonArray::class.java)
            val jsonObject = jsonArray.get(1).asJsonObject
            val deserializesObject = gson.fromJson(jsonObject, QuoteDto::class.java)

            return deserializesObject
        }
    }
    return null
}

fun String?.toErrorDto(): QuoteDto {
    return QuoteDto(
        error = this.orEmpty()
    )
}
