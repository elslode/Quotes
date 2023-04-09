package com.elsloude.quotes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.elsloude.quotes.common.State
import com.elsloude.quotes.data.QuotesRepositoryImpl
import com.elsloude.quotes.domain.usecase.CloseConnectionSocketUseCase
import com.elsloude.quotes.domain.usecase.GetQuotesUseCase
import com.elsloude.quotes.domain.usecase.OpenConnectionSocketUseCase
import com.elsloude.quotes.presentation.entity.QuoteUi
import com.elsloude.quotes.presentation.entity.QuoteView
import kotlinx.coroutines.flow.*

class MainViewModel : ViewModel() {

    private val repository = QuotesRepositoryImpl()
    private val openConnectSocket = OpenConnectionSocketUseCase(repository)
    private val closeConnectSocket = CloseConnectionSocketUseCase(repository)
    private val getQuotes = GetQuotesUseCase(repository)

    private val savedValues = hashMapOf<String, QuoteUi>()

    private val _quotesFlow: MutableStateFlow<State<List<QuoteView>>> = MutableStateFlow(State.Loading)
    val quotesFlow: StateFlow<State<List<QuoteView>>> = _quotesFlow.asStateFlow()

    fun openConnection() {
        openConnectSocket.invoke()
    }

    fun closeConnection() {
        closeConnectSocket.invoke()
    }

    suspend fun getQuotes() {
        getQuotes.invoke()
            .map { quote ->
                ConverterQuoteToList().convert(
                    savedMap = savedValues,
                    currentValue = quote
                )
            }
            .onStart {
                val loading = State.Loading
                _quotesFlow.emit(loading)
            }
            .collect {
                Log.d("onViewCreated", "getQuotes: $it")
                val result = State.Success(it)
                _quotesFlow.emit(result)
            }
    }
}