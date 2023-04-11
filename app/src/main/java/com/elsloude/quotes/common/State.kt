package com.elsloude.quotes.common

sealed class State<out R> {
    data class Success<out T>(val data: T? = null) : State<T>()
    data class Error(val error: String) : State<Nothing>()
    object Loading : State<Nothing>()
}
