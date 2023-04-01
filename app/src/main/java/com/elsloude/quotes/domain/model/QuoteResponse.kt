package com.elsloude.quotes.domain.model

data class QuoteResponse(
    val name: String,
    val c: String,
    val pcp: Double,
    val ltp: Double,
    val ltr: String,
    val chg: Double
)
