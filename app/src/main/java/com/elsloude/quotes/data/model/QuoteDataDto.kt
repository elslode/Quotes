package com.elsloude.quotes.data.model

import kotlinx.serialization.Serializable

@Serializable
data class QuoteDataDto(
    val c: String,
    val chg: Double,
    val ltp: Double,
    val ltr: String,
    val name: String,
    val pcp: Double
)
