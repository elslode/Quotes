package com.elsloude.quotes

import android.app.Application
import com.elsloude.quotes.di.DaggerAppComponent

class App: Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}