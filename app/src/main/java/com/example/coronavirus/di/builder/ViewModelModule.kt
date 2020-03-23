package com.example.coronavirus.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coronavirus.di.builder.AppViewModelBuilder
import com.example.coronavirus.di.builder.RepositoryBuilder
import com.example.coronavirus.di.builder.ViewModelFactory
import com.example.coronavirus.di.qualifier.ViewModelKey
import com.example.coronavirus.ui.CoronaStatsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [

        (AppViewModelBuilder::class)
    ]
//(RepositoryBuilder::class),
)
abstract class ViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(CoronaStatsViewModel::class)
//    abstract fun bindCoronaViewModel(viewModel: CoronaStatsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}