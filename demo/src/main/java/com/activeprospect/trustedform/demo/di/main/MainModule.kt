package com.activeprospect.trustedform.demo.di.main

import com.activeprospect.trustedform.demo.presentation.view.main.MainNavigator
import com.activeprospect.trustedform.demo.presentation.view.main.MainNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
interface MainModule {

    @Binds
    fun bindNavigator(navigator: MainNavigatorImpl): MainNavigator
}
