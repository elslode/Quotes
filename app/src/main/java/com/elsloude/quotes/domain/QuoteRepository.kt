package com.elsloude.quotes.domain

import com.elsloude.quotes.domain.model.QuoteResponse

interface QuoteRepository {
    fun getQuotes(): List<QuoteResponse>
    fun openSocketConnection()
    fun closeSocketConnection()
}