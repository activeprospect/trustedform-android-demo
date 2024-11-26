package com.activeprospect.trustedform.demo.di.demoform.demoformpage2

import androidx.fragment.app.Fragment
import com.activeprospect.trustedform.demo.di.demoform.demoformcompleted.DemoFormCompletedModule
import com.activeprospect.trustedform.demo.presentation.view.demoform.AdvertiserSuggestionScreenFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [DemoFormCompletedModule::class])
interface DemoFormComposePage2Component {

    fun inject(fragment: AdvertiserSuggestionScreenFragment)

    @Subcomponent.Factory
    interface Factory {

        fun bindFragment(@BindsInstance fragment: Fragment): DemoFormComposePage2Component
    }
}