package com.elsloude.quotes.domain.usecase

import com.elsloude.quotes.domain.QuoteRepository

class CloseConnectionSocketUseCase(
    private val repository: QuoteRepository
) {
    operator fun invoke() {
        repository.closeConnectionSocket()
    }
}