package com.elsloude.quotes.data

import com.elsloude.quotes.data.network.QuoteWebSocketContractsImpl
import com.elsloude.quotes.data.network.WebSocketProvider
import com.elsloude.quotes.data.network.WebSocketStateDto
import com.elsloude.quotes.domain.QuoteRepository
import com.elsloude.quotes.domain.model.QuoteResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuotesRepositoryImpl : QuoteRepository {

    private val webSocketContract = QuoteWebSocketContractsImpl()
    private val socket = WebSocketProvider(webSocketContract)
    private val gson = Gson()

    override fun getQuotesFlow(): Flow<QuoteResponse> {
        return webSocketContract.quoteStateFlow.map { response ->
                when (response) {
                    is WebSocketStateDto.Error -> {
                        throw java.lang.RuntimeException(response.messageError)
                    }
                    is WebSocketStateDto.QuoteData -> {
                        response.quoteData.mapToQuoteResponseDto(gson).toDomainModel()
                    }
                }
            }
    }

    override fun openConnectionSocket() {
        socket.startWebSocket()
    }

    override fun closeConnectionSocket() {
        socket.closeConnection()
    }
}