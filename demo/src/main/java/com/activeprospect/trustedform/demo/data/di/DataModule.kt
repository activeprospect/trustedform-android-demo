package com.activeprospect.trustedform.demo.data.di

import dagger.Module

@Module(
    includes = [
        RetrofitModule::class,
        EndpointsModule::class,
        RepositoriesModule::class,
        PreferencesModule::class
    ]
)
interface DataModule
