package com.elsloude.quotes.presentation

import androidx.lifecycle.ViewModel
import com.elsloude.quotes.data.QuotesRepositoryImpl
import com.elsloude.quotes.domain.usecase.CloseConnectionSocketUseCase
import com.elsloude.quotes.domain.usecase.GetQuotesUseCase
import com.elsloude.quotes.domain.usecase.OpenConnectionSocketUseCase
import com.elsloude.quotes.presentation.entity.QuoteUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val repository = QuotesRepositoryImpl()
    private val openConnectSocket = OpenConnectionSocketUseCase(repository)
    private val closeConnectSocket = CloseConnectionSocketUseCase(repository)
    private val getQuotes = GetQuotesUseCase(repository)

    private val _quotesFlow: MutableStateFlow<QuoteUi> = MutableStateFlow(QuoteUi())
    val quotesFlow: StateFlow<QuoteUi> = _quotesFlow.asStateFlow()

    fun openConnection() {
        openConnectSocket.invoke()
    }

    fun closeConnection() {
        closeConnectSocket.invoke()
    }

    suspend fun getQuotes() {
        getQuotes.invoke().collect {

        }
    }
}