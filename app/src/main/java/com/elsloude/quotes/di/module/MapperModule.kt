package com.elsloude.quotes.di.module

import com.elsloude.quotes.presentation.ConverterQuoteToList
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun provideQuoteToListMapper(): ConverterQuoteToList {
        return ConverterQuoteToList()
    }
}