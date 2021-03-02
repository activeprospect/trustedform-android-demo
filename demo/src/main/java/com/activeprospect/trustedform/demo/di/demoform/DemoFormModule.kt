package com.activeprospect.trustedform.demo.di.demoform

import androidx.lifecycle.ViewModel
import com.activeprospect.trustedform.demo.common.viewmodels.ViewModelKey
import com.activeprospect.trustedform.demo.presentation.view.demoform.DemoFormViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DemoFormModule {

    @Binds
    @IntoMap
    @ViewModelKey(DemoFormViewModel::class)
    fun bindViewModel(viewModel: DemoFormViewModel): ViewModel
}