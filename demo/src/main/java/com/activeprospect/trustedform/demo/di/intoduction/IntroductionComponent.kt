package com.activeprospect.trustedform.demo.di.intoduction

import com.activeprospect.trustedform.demo.di.FragmentScope
import com.activeprospect.trustedform.demo.di.signin.SignInComponent
import com.activeprospect.trustedform.demo.di.splash.SplashComponent
import com.activeprospect.trustedform.demo.di.whatIs.WhatIsComponent
import com.activeprospect.trustedform.demo.di.whois.WhoIsComponent
import com.activeprospect.trustedform.demo.presentation.view.main.MainFragment
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [IntroductionModule::class])
interface IntroductionComponent {

    fun inject(fragment: MainFragment)

    fun getSplashComponentFactory(): SplashComponent.Factory
    fun getWhoIsComponentFactory(): WhoIsComponent.Factory
    fun getWhatIsComponent(): WhatIsComponent.Factory
    fun getSignInComponent(): SignInComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun bindFragment(@BindsInstance fragment: MainFragment): IntroductionComponent
    }
}
