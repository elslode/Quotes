package com.elsloude.quotes.di.module

import com.elsloude.quotes.data.QuotesRepositoryImpl
import com.elsloude.quotes.data.network.WebSocketProvider
import com.elsloude.quotes.di.scope.ApplicationScope
import com.elsloude.quotes.domain.QuotesRepository
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
class DataModule {

    @Provides
    @ApplicationScope
    fun provideQuotesRepository(
        gson: Gson,
        provideWebSocketProvider: WebSocketProvider
    ): QuotesRepository {
        return QuotesRepositoryImpl(gson, provideWebSocketProvider)
    }

    @Provides
    fun provideWebSocketProvider(client: OkHttpClient): WebSocketProvider {
        return WebSocketProvider(client)
    }

    @Provides
    @ApplicationScope
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .hostnameVerifier { _, _ -> true }
            .retryOnConnectionFailure(true)
            .build()
    }
}