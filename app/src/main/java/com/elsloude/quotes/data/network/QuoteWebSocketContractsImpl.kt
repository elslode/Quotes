package com.elsloude.quotes.data.network

import android.util.Log
import com.elsloude.quotes.data.toDomain
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class QuoteWebSocketContractsImpl : QuoteWebSocketContracts {

    private val stateFlow = MutableSharedFlow<WebSocketStateDto>()

    override val quoteStateFlow: Flow<WebSocketStateDto>
        get() = stateFlow.asSharedFlow()

    override fun setQuote(response: WebSocketStateDto) {
        CoroutineScope(Dispatchers.IO).launch {
            stateFlow.tryEmit(response)
        }
    }
}