package com.elsloude.quotes.domain.usecase

import com.elsloude.quotes.domain.QuoteRepository

class OpenConnectionSocketUseCase(
    private val repository: QuoteRepository
) {
    operator fun invoke() {
        repository.openConnectionSocket()
    }
}