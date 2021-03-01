package com.activeprospect.trustedform.demo.di.splash

import androidx.lifecycle.ViewModel
import com.activeprospect.trustedform.demo.common.viewmodels.ViewModelKey
import com.activeprospect.trustedform.demo.presentation.view.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SplashModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindViewModel(viewModel: SplashViewModel): ViewModel
}
