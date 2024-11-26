package com.activeprospect.trustedform.demo.di.demoform

import androidx.fragment.app.Fragment
import com.activeprospect.trustedform.demo.presentation.view.demoform.ContactInfoScreenFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [DemoFormModule::class])
interface DemoFormComposeComponent {

    fun inject(fragment: ContactInfoScreenFragment)

    @Subcomponent.Factory
    interface Factory {

        fun bindFragment(@BindsInstance fragment: Fragment): DemoFormComposeComponent
    }
}