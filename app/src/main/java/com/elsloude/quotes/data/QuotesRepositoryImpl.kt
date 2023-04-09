package com.elsloude.quotes.data

import com.elsloude.quotes.data.entity.QuoteDto
import com.elsloude.quotes.data.network.QuoteWebSocketCallback
import com.elsloude.quotes.data.network.WebSocketProvider
import com.elsloude.quotes.domain.QuoteRepository
import com.elsloude.quotes.domain.entity.QuoteResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class QuotesRepositoryImpl : QuoteRepository, QuoteWebSocketCallback {

    private val gson = Gson()
    private val socket = WebSocketProvider(this, gson)

    private val _quoteFlow = MutableSharedFlow<QuoteResponse>()
    override val flow: SharedFlow<QuoteResponse>
        get() = _quoteFlow.asSharedFlow()

    override fun openConnectionSocket() {
        socket.startWebSocket()
    }

    override fun closeConnectionSocket() {
        socket.closeConnection()
    }

    override fun onQuoteDataReceived(quoteDataDto: QuoteDto?) {
        CoroutineScope(Dispatchers.IO).launch {
            _quoteFlow.emit(quoteDataDto.toDomainModel())
        }
    }

    override fun onWebSocketError(error: QuoteDto?) {
        CoroutineScope(Dispatchers.IO).launch {
            _quoteFlow.emit(error.toDomainModel())
        }
    }
}