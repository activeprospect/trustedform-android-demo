package com.activeprospect.trustedform.demo.di.bottommenu

import com.activeprospect.trustedform.demo.presentation.view.bottommenu.BottomMenuNavigator
import com.activeprospect.trustedform.demo.presentation.view.bottommenu.BottomMenuNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
interface BottomMenuModule {

    @Binds
    fun bindBottomMenuNavigator(navigator: BottomMenuNavigatorImpl): BottomMenuNavigator
}
