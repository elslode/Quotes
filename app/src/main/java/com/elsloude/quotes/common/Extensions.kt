package com.elsloude.quotes.common

import android.view.View
import com.elsloude.quotes.data.entity.QuotesRequestDto
import com.elsloude.quotes.presentation.entity.QuoteUi
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import java.math.BigDecimal
import java.util.Objects

fun QuotesRequestDto.toJsonArray(): JsonArray {
    return JsonArray(
        listOf(
            JsonPrimitive(messageType),
            JsonArray(symbols.list.map { JsonPrimitive(it) })
        )
    )
}

fun HashMap<String, QuoteUi>.toList(): List<QuoteUi> {
    val resultList = mutableListOf<QuoteUi>()
    this.map {
        resultList.add(it.value)
    }

    return resultList
}

fun <T> Comparable<T>?.isNull(): Boolean {
    return this == null
}
