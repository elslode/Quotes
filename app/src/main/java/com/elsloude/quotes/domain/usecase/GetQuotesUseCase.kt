package com.elsloude.quotes.domain.usecase

import com.elsloude.quotes.domain.QuoteRepository

class GetQuotesUseCase(
    val repository: QuoteRepository
) {
    operator fun invoke() = repository.getQuotesFlow()
}