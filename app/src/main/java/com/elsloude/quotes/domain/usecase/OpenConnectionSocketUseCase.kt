package com.elsloude.quotes.domain.usecase

import com.elsloude.quotes.domain.QuotesRepository
import javax.inject.Inject

class OpenConnectionSocketUseCase @Inject constructor (
    private val repository: QuotesRepository
) {
    operator fun invoke() {
        repository.openConnectionSocket()
    }
}