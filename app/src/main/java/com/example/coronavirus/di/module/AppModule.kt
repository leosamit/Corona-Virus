package com.example.coronavirus.di.module

import com.example.coronavirus.util.CoroutineDispatcherProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcherProvider {
        return CoroutineDispatcherProvider(
            Dispatchers.Main,
            Dispatchers.IO,
            Dispatchers.Default
        )
    }
}