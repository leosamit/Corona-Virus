package com.example.coronavirus.di.builder

import com.example.coronavirus.ui.about.AboutFragment
import com.example.coronavirus.ui.country.CoronaStatsFragment
import com.example.coronavirus.ui.country.CountryHistoricalFragment
import com.example.coronavirus.ui.world.WorldDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProviders {
    @ContributesAndroidInjector
    abstract fun provideCoronaStatsFragment(): CoronaStatsFragment

    @ContributesAndroidInjector
    abstract fun provideCoronaWorldDetail(): WorldDetailFragment

    @ContributesAndroidInjector
    abstract fun provideCountryHistorical(): CountryHistoricalFragment

    @ContributesAndroidInjector
    abstract fun provideAboutFragment(): AboutFragment
}