package com.activeprospect.trustedform.demo

import com.activeprospect.trustedform.demo.di.DaggerApplicationComponent
import com.activeprospect.trustedform.demo.di.InjectorApplication

class TrustedFormApplication : InjectorApplication() {

    override fun onCreate() {
        super.onCreate()

        initInjector()
    }

    private fun initInjector() {
        DaggerApplicationComponent.factory().application(this).also {
            initInjector(it)
            it.inject(this)
        }
    }
}