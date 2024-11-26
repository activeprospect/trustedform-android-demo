package com.activeprospect.trustedform.demo.di

import android.app.Application
import com.activeprospect.trustedform.demo.di.bottommenu.BottomMenuComponent
import com.activeprospect.trustedform.demo.di.bottommenu.BottomMenuInjector
import com.activeprospect.trustedform.demo.di.contact.ContactInjector
import com.activeprospect.trustedform.demo.di.demoform.DemoFormInjector
import com.activeprospect.trustedform.demo.di.demoform.demoformcompleted.DemoFormCompletedInjector
import com.activeprospect.trustedform.demo.di.demoform.demoformpage2.DemoFormComposePage2Injector
import com.activeprospect.trustedform.demo.di.home.HomeInjector
import com.activeprospect.trustedform.demo.di.intoduction.IntroductionComponent
import com.activeprospect.trustedform.demo.di.intoduction.IntroductionInjector
import com.activeprospect.trustedform.demo.di.main.MainComponent
import com.activeprospect.trustedform.demo.di.main.MainInjector
import com.activeprospect.trustedform.demo.di.signin.SignInInjector
import com.activeprospect.trustedform.demo.di.splash.SplashInjector
import com.activeprospect.trustedform.demo.di.whatIs.WhatIsInjector
import com.activeprospect.trustedform.demo.di.whois.WhoIsInjector
import com.activeprospect.trustedform.demo.presentation.view.bottommenu.BottomMenuFragment
import com.activeprospect.trustedform.demo.presentation.view.contact.ContactFragment
import com.activeprospect.trustedform.demo.presentation.view.demoform.ContactInfoScreenFragment
import com.activeprospect.trustedform.demo.presentation.view.demoform.AdvertiserSuggestionScreenFragment
import com.activeprospect.trustedform.demo.presentation.view.demoform.demoformcompleted.DemoFormCompletedFragment
import com.activeprospect.trustedform.demo.presentation.view.home.HomeFragment
import com.activeprospect.trustedform.demo.presentation.view.main.MainActivity
import com.activeprospect.trustedform.demo.presentation.view.main.MainFragment
import com.activeprospect.trustedform.demo.presentation.view.signin.SignInFragment
import com.activeprospect.trustedform.demo.presentation.view.splash.SplashFragment
import com.activeprospect.trustedform.demo.presentation.view.whatis.WhatIsFragment
import com.activeprospect.trustedform.demo.presentation.view.whois.WhoIsFragment

open class InjectorApplication : Application(),
    ContactInjector,
    DemoFormInjector,
    DemoFormCompletedInjector,
    DemoFormComposePage2Injector,
    BottomMenuInjector,
    HomeInjector,
    MainInjector,
    WhoIsInjector,
    IntroductionInjector,
    WhatIsInjector,
    SignInInjector,
    SplashInjector {
    private lateinit var appComponent: ApplicationComponent

    private var mainComponent: MainComponent? = null
    private var introductionComponent: IntroductionComponent? = null
    private var bottomMenuComponent: BottomMenuComponent? = null

    protected fun initInjector(appComponent: ApplicationComponent) {
        this.appComponent = appComponent
    }

    override fun inject(activity: MainActivity) {
        if (mainComponent == null) {
            mainComponent = appComponent.getMainComponentFactory().bindActivity(activity)
        }
        mainComponent?.inject(activity)
    }

    override fun inject(fragment: MainFragment) {
        if (introductionComponent == null) {
            introductionComponent =
                mainComponent?.getIntroductionComponentFactory()?.bindFragment(fragment)
        }
        introductionComponent?.inject(fragment)
    }

    override fun inject(fragment: BottomMenuFragment) {
        if (bottomMenuComponent == null) {
            bottomMenuComponent =
                mainComponent?.getBottomMenuComponentFactory()?.bindFragment(fragment)
        }
        bottomMenuComponent?.inject(fragment)
    }

    override fun inject(fragment: SplashFragment) =
        introductionComponent?.getSplashComponentFactory()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()

    override fun inject(fragment: WhoIsFragment) =
        introductionComponent?.getWhoIsComponentFactory()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()

    override fun inject(fragment: WhatIsFragment) =
        introductionComponent?.getWhatIsComponent()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()

    override fun inject(fragment: SignInFragment) =
        introductionComponent?.getSignInComponent()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()

    override fun inject(fragment: HomeFragment) =
        bottomMenuComponent?.getHomeComponent()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()

    override fun inject(fragment: ContactFragment) =
        bottomMenuComponent?.getContactComponent()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()

    override fun inject(fragment: ContactInfoScreenFragment) =
        bottomMenuComponent?.getDemoFormComposeComponentFactory()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()

    override fun inject(fragment: DemoFormCompletedFragment) =
        bottomMenuComponent?.getDemoFormCompletedComponentFactory()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()

    override fun inject(fragment: AdvertiserSuggestionScreenFragment) =
        bottomMenuComponent?.getDemoFormComposePage2Factory()
            ?.bindFragment(fragment)
            ?.inject(fragment) ?: throw IllegalStateException()

    override fun clearMainComponent() {
        mainComponent = null
    }

    override fun clearIntroductionComponent() {
        introductionComponent = null
    }

    override fun clearBottomMenuComponent() {
        bottomMenuComponent = null
    }
}
