package com.elsloude.quotes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elsloude.quotes.common.State
import com.elsloude.quotes.data.QuotesRepositoryImpl
import com.elsloude.quotes.domain.usecase.CloseConnectionSocketUseCase
import com.elsloude.quotes.domain.usecase.GetQuotesUseCase
import com.elsloude.quotes.domain.usecase.OpenConnectionSocketUseCase
import com.elsloude.quotes.presentation.entity.QuoteUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = QuotesRepositoryImpl()
    private val openConnectSocket = OpenConnectionSocketUseCase(repository)
    private val closeConnectSocket = CloseConnectionSocketUseCase(repository)
    private val getQuotes = GetQuotesUseCase(repository)

    private val savedValues = hashMapOf<String, QuoteUi>()
    private val currentValues = hashMapOf<String, QuoteUi>()

    private val _quotesFlow: MutableStateFlow<State<List<QuoteUi>>> =
        MutableStateFlow(State.Loading)
    val quotesFlow: StateFlow<State<List<QuoteUi>>> = _quotesFlow

    fun openConnection() {
        openConnectSocket.invoke()
    }

    fun closeConnection() {
        closeConnectSocket.invoke()
    }

    fun getQuotes() {
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

                }
                .collect {
                    val result = State.Success(it)
                    _quotesFlow.emit(result)
                }
        }

    }
}