package com.activeprospect.trustedform.demo.di.bottommenu

import com.activeprospect.trustedform.demo.presentation.view.bottommenu.BottomMenuFragment

interface BottomMenuInjector {

    fun inject(fragment: BottomMenuFragment)
    fun clearBottomMenuComponent()
}