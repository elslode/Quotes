package com.elsloude.quotes.data

import com.elsloude.quotes.domain.QuoteRepository
import com.elsloude.quotes.domain.model.QuoteResponse

class QuotesRepositoryImpl: QuoteRepository {
    override fun getQuotes(): List<QuoteResponse> {
        TODO("Not yet implemented")
    }

    override fun openSocketConnection() {
        TODO("Not yet implemented")
    }

    override fun closeSocketConnection() {
        TODO("Not yet implemented")
    }

}