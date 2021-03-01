package com.activeprospect.trustedform.demo.di.demoform

import androidx.fragment.app.Fragment
import com.activeprospect.trustedform.demo.presentation.view.demoform.DemoFormFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [DemoFormModule::class])
interface DemoFormComponent {

    fun inject(fragment: DemoFormFragment)

    @Subcomponent.Factory
    interface Factory {

        fun bindFragment(@BindsInstance fragment: Fragment): DemoFormComponent
    }
}