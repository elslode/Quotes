package com.elsloude.quotes.data

import com.elsloude.quotes.data.model.QuoteDataDto
import com.elsloude.quotes.data.model.QuoteResponseDto
import com.elsloude.quotes.data.network.WebSocketStateDto
import com.elsloude.quotes.domain.model.QuoteResponse
import com.elsloude.quotes.domain.model.QuoteState
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser

private const val USER_TYPE = "\"userData\""
private const val QUOTE_TYPE = "\"q\""

//fun QuoteDataDto?.mapQuoteDataToResponseDto(): QuoteResponseDto {
//    return QuoteResponseDto(
//        companyName = this?.name ?: "",
//        ticker = this?.c ?: "",
//        percentChangeFromPreviousClose = this?.pcp  ?: 0.0,
//        lastTradePrice = this?.ltp ?: 0.0,
//        lastTradeExchange = this?.ltr ?: "",
//        priceChangeInPoints = this?.chg ?: 0.0
//    )
//}

fun QuoteDataDto?.toDomainModel(): QuoteResponse {
    return QuoteResponse(
        companyName = this?.companyName.orEmpty(),
        ticker = this?.ticker.orEmpty(),
        percentChangeFromPreviousClose = this?.percentChangeFromPreviousClose ?: 0.0,
        lastTradePrice = this?.lastTradePrice ?: 0.0,
        lastTradeExchange = this?.lastTradeExchange.orEmpty(),
        priceChangeInPoints = this?.priceChangeInPoints ?: 0.0
    )
}

fun WebSocketStateDto.toDomain(gson: Gson): QuoteState {
    return when(this) {
        is WebSocketStateDto.Error -> {
            QuoteState.Error(this.messageError)
        }
        is WebSocketStateDto.QuoteData -> {
            val quoteDto = this.quoteData.mapToQuoteResponseDto(gson)
            val quoteDomain = quoteDto.toDomainModel()
            QuoteState.QuoteData(quoteDomain)
        }
    }
}

fun String.mapToQuoteResponseDto(gson: Gson): QuoteDataDto? {

    val jsonElement = JsonParser.parseString(this)

    if (jsonElement is JsonArray && jsonElement.size() >= 2) {
        val messageType = jsonElement.first().asString ?: return null
        if (messageType == "q") {
            val jsonArray = gson.fromJson(jsonElement, JsonArray::class.java)
            val jsonObject = jsonArray.get(1).asJsonObject
            val deserializesObject = gson.fromJson(jsonObject, QuoteDataDto::class.java)
            return deserializesObject
        }
    }
    return null
}

