package com.elsloude.quotes.domain.usecase

import com.elsloude.quotes.domain.QuotesRepository
import com.elsloude.quotes.domain.entity.QuoteResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository: QuotesRepository
) {
    operator fun invoke(): Flow<QuoteResponse> = repository.quotesFlow
}