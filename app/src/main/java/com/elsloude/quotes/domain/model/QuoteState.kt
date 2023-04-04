package com.elsloude.quotes.domain.model

sealed class QuoteState {
    class QuoteData(val quoteData: QuoteResponse) : QuoteState()
    class Error(val messageError: String) : QuoteState()
}