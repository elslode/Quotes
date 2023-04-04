package com.elsloude.quotes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class QuotesRequestDto(
    val messageType: String = "quotes",
    val symbols: SymbolList = SymbolList()
)

@Serializable
data class SymbolList(val list: List<String> = listSymbols)

