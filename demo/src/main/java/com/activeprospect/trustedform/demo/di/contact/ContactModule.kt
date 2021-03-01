package com.activeprospect.trustedform.demo.di.contact

import androidx.lifecycle.ViewModel
import com.activeprospect.trustedform.demo.common.viewmodels.ViewModelKey
import com.activeprospect.trustedform.demo.presentation.view.contact.ContactViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ContactModule {

    @Binds
    @IntoMap
    @ViewModelKey(ContactViewModel::class)
    fun bindViewModel(viewModel: ContactViewModel): ViewModel
}
