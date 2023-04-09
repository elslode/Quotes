package com.elsloude.quotes.domain.usecase

import android.util.Log
import com.elsloude.quotes.domain.QuoteRepository
import com.elsloude.quotes.presentation.entity.QuoteUi
import com.elsloude.quotes.presentation.entity.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class GetQuotesUseCase(
    private val repository: QuoteRepository
) {
    operator fun invoke(): Flow<QuoteUi> = repository.flow
        .map { response ->
            response.toUiModel()
        }
}