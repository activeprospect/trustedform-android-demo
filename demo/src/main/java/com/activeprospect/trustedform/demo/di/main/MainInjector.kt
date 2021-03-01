package com.activeprospect.trustedform.demo.di.main

import com.activeprospect.trustedform.demo.presentation.view.main.MainActivity

interface MainInjector {

    fun inject(activity: MainActivity)
    fun clearMainComponent()
}