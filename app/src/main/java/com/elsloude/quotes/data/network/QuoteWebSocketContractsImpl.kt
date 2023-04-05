package com.elsloude.quotes.data.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class QuoteWebSocketContractsImpl : QuoteWebSocketContracts {

    private val stateFlow: MutableSharedFlow<WebSocketStateDto> = MutableSharedFlow()
    override val quoteStateFlow: Flow<WebSocketStateDto> = stateFlow

    override fun setQuote(response: WebSocketStateDto) {
        CoroutineScope(Dispatchers.IO).launch {
            stateFlow.emit(response)
        }
    }
}