package com.activeprospect.trustedform.demo.di.intoduction

import com.activeprospect.trustedform.demo.presentation.view.main.MainFragment

interface IntroductionInjector {

    fun inject(fragment: MainFragment)
    fun clearIntroductionComponent()
}