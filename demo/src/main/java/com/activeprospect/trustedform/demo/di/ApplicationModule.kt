package com.activeprospect.trustedform.demo.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
interface ApplicationModule {

    @Binds
    fun bindAppContext(application: Application): Context
}