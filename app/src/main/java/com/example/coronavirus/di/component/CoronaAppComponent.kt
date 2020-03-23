package com.example.coronavirus.di.component

import android.app.Application
import com.example.coronavirus.CoronaApplication
import com.example.coronavirus.di.builder.ActivityBuilder
import com.example.coronavirus.di.module.AppModule
import com.example.coronavirus.di.module.ContextModule
import com.example.coronavirus.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ContextModule::class,
        ActivityBuilder::class]
)
interface CoronaAppComponent : AndroidInjector<CoronaApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoronaAppComponent
    }
}