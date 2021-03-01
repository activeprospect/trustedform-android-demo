package com.activeprospect.trustedform.demo.di.splash

import androidx.fragment.app.Fragment
import com.activeprospect.trustedform.demo.presentation.view.splash.SplashFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {

    fun inject(fragment: SplashFragment)

    @Subcomponent.Factory
    interface Factory {
        fun bindFragment(@BindsInstance fragment: Fragment): SplashComponent
    }
}
