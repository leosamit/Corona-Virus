package com.example.coronavirus.util

import kotlinx.coroutines.CoroutineDispatcher

data class CoroutineDispatcherProvider(
    val mainDispatcher: CoroutineDispatcher,
    val ioDispatcher: CoroutineDispatcher,
    val computationDispatcher: CoroutineDispatcher
)