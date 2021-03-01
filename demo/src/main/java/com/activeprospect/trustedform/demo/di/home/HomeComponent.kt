package com.activeprospect.trustedform.demo.di.home

import androidx.fragment.app.Fragment
import com.activeprospect.trustedform.demo.presentation.view.home.HomeFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {

    fun inject(fragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory {
        fun bindFragment(@BindsInstance fragment: Fragment): HomeComponent
    }
}
