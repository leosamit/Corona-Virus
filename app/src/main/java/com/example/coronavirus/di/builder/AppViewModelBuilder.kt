package com.example.coronavirus.di.builder

import androidx.lifecycle.ViewModel
import com.example.coronavirus.di.qualifier.ViewModelKey
import com.example.coronavirus.ui.CoronaStatsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(CoronaStatsViewModel::class)
    abstract fun bindCoronaStatsViewModel(homeViewModel: CoronaStatsViewModel): ViewModel
}