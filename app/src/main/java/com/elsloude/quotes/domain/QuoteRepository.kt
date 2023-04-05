package com.elsloude.quotes.domain

import com.elsloude.quotes.domain.model.QuoteResponse
import com.elsloude.quotes.domain.model.QuoteState
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    fun getQuotesFlow(): Flow<QuoteResponse>
    fun openConnectionSocket()
    fun closeConnectionSocket()
}