package com.elsloude.quotes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.elsloude.quotes.data.QuotesRepositoryImpl
import com.elsloude.quotes.domain.usecase.CloseConnectionSocketUseCase
import com.elsloude.quotes.domain.usecase.GetQuotesUseCase
import com.elsloude.quotes.domain.usecase.OpenConnectionSocketUseCase
import kotlinx.coroutines.flow.onEach

class MainViewModel : ViewModel() {

    private val repository = QuotesRepositoryImpl()
    private val openConnectSocket = OpenConnectionSocketUseCase(repository)
    private val closeConnectSocket = CloseConnectionSocketUseCase(repository)
    private val getQuotes = GetQuotesUseCase(repository)

    fun openConnection() {
        openConnectSocket.invoke()
    }

    fun closeConnection() {
        closeConnectSocket.invoke()
    }

    fun getQuotes() {
        getQuotes.invoke().onEach {
            Log.d("MainViewModel", "getQuotes: $it")
        }
    }
}