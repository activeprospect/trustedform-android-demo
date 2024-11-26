package com.activeprospect.trustedform.demo.di.bottommenu

import com.activeprospect.trustedform.demo.di.FragmentScope
import com.activeprospect.trustedform.demo.di.contact.ContactComponent
import com.activeprospect.trustedform.demo.di.demoform.DemoFormComposeComponent
import com.activeprospect.trustedform.demo.di.demoform.demoformcompleted.DemoFormCompletedComponent
import com.activeprospect.trustedform.demo.di.demoform.demoformpage2.DemoFormComposePage2Component
import com.activeprospect.trustedform.demo.di.home.HomeComponent
import com.activeprospect.trustedform.demo.presentation.view.bottommenu.BottomMenuFragment
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [BottomMenuModule::class])
interface BottomMenuComponent {

    fun inject(fragment: BottomMenuFragment)

    fun getHomeComponent(): HomeComponent.Factory
    fun getContactComponent(): ContactComponent.Factory
    fun getDemoFormComposeComponentFactory(): DemoFormComposeComponent.Factory
    fun getDemoFormCompletedComponentFactory(): DemoFormCompletedComponent.Factory
    fun getDemoFormComposePage2Factory(): DemoFormComposePage2Component.Factory

    @Subcomponent.Factory
    interface Factory {
        fun bindFragment(@BindsInstance fragment: BottomMenuFragment): BottomMenuComponent
    }
}
