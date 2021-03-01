package com.activeprospect.trustedform.demo.di.demoform.demoformcompleted

import androidx.fragment.app.Fragment
import com.activeprospect.trustedform.demo.presentation.view.demoform.demoformcompleted.DemoFormCompletedFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [DemoFormCompletedModule::class])
interface DemoFormCompletedComponent {

    fun inject(fragment: DemoFormCompletedFragment)

    @Subcomponent.Factory
    interface Factory {

        fun bindFragment(@BindsInstance fragment: Fragment): DemoFormCompletedComponent
    }
}