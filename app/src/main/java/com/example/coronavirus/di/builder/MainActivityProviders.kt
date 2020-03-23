package com.example.coronavirus.di.builder

import com.example.coronavirus.ui.CoronaStatsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProviders{
    @ContributesAndroidInjector
    abstract fun provideCoronaStatsFragment(): CoronaStatsFragment
}