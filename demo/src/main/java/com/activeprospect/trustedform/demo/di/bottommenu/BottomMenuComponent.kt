package com.activeprospect.trustedform.demo.di.bottommenu

import com.activeprospect.trustedform.demo.di.FragmentScope
import com.activeprospect.trustedform.demo.di.contact.ContactComponent
import com.activeprospect.trustedform.demo.di.demoform.DemoFormComponent
import com.activeprospect.trustedform.demo.di.demoform.demoformcompleted.DemoFormCompletedComponent
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
    fun getDemoFormComponentFactory(): DemoFormComponent.Factory
    fun getDemoFormCompletedComponentFactory(): DemoFormCompletedComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun bindFragment(@BindsInstance fragment: BottomMenuFragment): BottomMenuComponent
    }
}
