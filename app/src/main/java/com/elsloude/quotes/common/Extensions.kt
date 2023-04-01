package com.elsloude.quotes.common

import com.elsloude.quotes.data.model.QuotesRequestDto
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive

fun QuotesRequestDto.toJsonArray(): JsonArray {
    return JsonArray(
        listOf(
            JsonPrimitive(messageType),
            JsonArray(symbols.list.map { JsonPrimitive(it) })
        )
    )
}