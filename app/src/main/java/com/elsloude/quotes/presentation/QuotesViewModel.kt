package com.elsloude.quotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elsloude.quotes.common.State
import com.elsloude.quotes.domain.usecase.CloseConnectionSocketUseCase
import com.elsloude.quotes.domain.usecase.GetQuotesUseCase
import com.elsloude.quotes.domain.usecase.OpenConnectionSocketUseCase
import com.elsloude.quotes.presentation.entity.QuoteUi
import com.elsloude.quotes.presentation.entity.toUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class QuotesViewModel @Inject constructor(
    private val getQuotes: GetQuotesUseCase,
    private val openConnectSocket: OpenConnectionSocketUseCase,
    private val closeConnectSocket: CloseConnectionSocketUseCase,
    private val mapper: ConverterQuoteToList
) : ViewModel() {

    private val savedValues = hashMapOf<String, QuoteUi>()
    private val currentValues = hashMapOf<String, QuoteUi>()

    private val _quotesFlow = getQuotes.invoke()
        .map { quote ->
            quote.toUiModel().let { uiModel ->
                uiModel.ticker?.let {
                    currentValues[it] = uiModel
                }
            }
            ConverterQuoteToList().convert(
                savedMap = savedValues,
                currentMap = currentValues
            )
        }
        .map {
            State.Success(it)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = State.Loading
        )
    val quotesFlow: StateFlow<State<List<QuoteUi>>> = this._quotesFlow

    init {
        openConnectSocket.invoke()
        getQuotes()
    }

    override fun onCleared() {
        closeConnectSocket.invoke()
    }
}