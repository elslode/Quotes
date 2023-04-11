package com.elsloude.quotes.di

import android.app.Application
import com.elsloude.quotes.di.module.DataModule
import com.elsloude.quotes.di.module.MapperModule
import com.elsloude.quotes.di.module.ViewModelModule
import com.elsloude.quotes.di.scope.ApplicationScope
import com.elsloude.quotes.presentation.QuotesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ViewModelModule::class,
        DataModule::class,
        MapperModule::class
    ]
)
@Singleton
@ApplicationScope
interface AppComponent {

    fun inject(quotesFragment: QuotesFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}