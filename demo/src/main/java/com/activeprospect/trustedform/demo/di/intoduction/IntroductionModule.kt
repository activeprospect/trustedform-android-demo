package com.activeprospect.trustedform.demo.di.intoduction

import com.activeprospect.trustedform.demo.presentation.view.main.MainFragmentNavigator
import com.activeprospect.trustedform.demo.presentation.view.main.MainFragmentNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
interface IntroductionModule {

    @Binds
    fun bindIntroductionNavigator(navigator: MainFragmentNavigatorImpl): MainFragmentNavigator
}
