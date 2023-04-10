package com.elsloude.quotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elsloude.quotes.common.State
import com.elsloude.quotes.domain.usecase.CloseConnectionSocketUseCase
import com.elsloude.quotes.domain.usecase.GetQuotesUseCase
import com.elsloude.quotes.domain.usecase.OpenConnectionSocketUseCase
import com.elsloude.quotes.presentation.entity.QuoteUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotesViewModel @Inject constructor(
    private val getQuotes: GetQuotesUseCase,
    private val openConnectSocket: OpenConnectionSocketUseCase,
    private val closeConnectSocket: CloseConnectionSocketUseCase
) : ViewModel() {

    private val savedValues = hashMapOf<String, QuoteUi>()
    private val currentValues = hashMapOf<String, QuoteUi>()

    private val _quotesFlow: MutableStateFlow<State<List<QuoteUi>>> =
        MutableStateFlow(State.Loading)
    val quotesFlow: StateFlow<State<List<QuoteUi>>> = _quotesFlow

    fun closeConnection() {
        closeConnectSocket.invoke()
    }

    private fun getQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            getQuotes.invoke()
                .map { quote ->
                    quote.let { uiModel ->
                        uiModel.ticker?.let {
                            currentValues[it] = uiModel
                        }
                    }
                    ConverterQuoteToList().convert(
                        savedMap = savedValues,
                        currentMap = currentValues
                    )
                }
                .onEach {
                    val result = State.Success(it)
                    _quotesFlow.emit(result)
                }
                .collect()
        }
    }

    init {
        getQuotes()
        openConnectSocket.invoke()
    }
}