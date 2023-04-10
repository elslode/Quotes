package com.elsloude.quotes.domain

import com.elsloude.quotes.domain.entity.QuoteResponse
import kotlinx.coroutines.flow.SharedFlow

interface QuotesRepository {

    val flow: SharedFlow<QuoteResponse>
    fun openConnectionSocket()
    fun closeConnectionSocket()
}