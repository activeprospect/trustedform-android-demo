package com.activeprospect.trustedform.demo.di.contact

import androidx.fragment.app.Fragment
import com.activeprospect.trustedform.demo.presentation.view.contact.ContactFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ContactModule::class])
interface ContactComponent {

    fun inject(fragment: ContactFragment)

    @Subcomponent.Factory
    interface Factory {
        fun bindFragment(@BindsInstance fragment: Fragment): ContactComponent
    }
}
