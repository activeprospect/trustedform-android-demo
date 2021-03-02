package com.activeprospect.trustedform.demo.di.signin

import androidx.lifecycle.ViewModel
import com.activeprospect.trustedform.demo.common.viewmodels.ViewModelKey
import com.activeprospect.trustedform.demo.presentation.view.signin.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SignInModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun bindViewModel(viewModel: SignInViewModel): ViewModel
}
