package com.elsloude.quotes.domain.usecase

import com.elsloude.quotes.domain.QuotesRepository
import com.elsloude.quotes.presentation.entity.QuoteUi
import com.elsloude.quotes.presentation.entity.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val repository: QuotesRepository
) {
    operator fun invoke(): Flow<QuoteUi> = repository.flow
        .map { response ->
            response.toUiModel()
        }
}