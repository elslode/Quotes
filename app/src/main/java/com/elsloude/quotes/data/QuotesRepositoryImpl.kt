package com.elsloude.quotes.data

import com.elsloude.quotes.data.network.QuoteWebSocketContractsImpl
import com.elsloude.quotes.data.network.WebSocketProvider
import com.elsloude.quotes.domain.QuoteRepository
import com.elsloude.quotes.domain.model.QuoteState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuotesRepositoryImpl : QuoteRepository {

    private val webSocketContract = QuoteWebSocketContractsImpl()
    private val socket = WebSocketProvider()
    private val gson = Gson()

    override fun getQuotesFlow(): Flow<QuoteState> {
        return webSocketContract.quoteStateFlow.map {
            it.toDomain(gson)
        }
    }

    override fun openConnectionSocket() {
        socket.startWebSocket()
    }

    override fun closeConnectionSocket() {
        socket.closeConnection()
    }
}