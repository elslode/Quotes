package com.elsloude.quotes.di.module

import androidx.lifecycle.ViewModel
import com.elsloude.quotes.di.scope.ViewModelKey
import com.elsloude.quotes.presentation.QuotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(QuotesViewModel::class)
    fun bindMainViewModel(viewModel: QuotesViewModel): ViewModel

}