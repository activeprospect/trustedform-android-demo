package com.activeprospect.trustedform.demo

import com.activeprospect.trustedform.BuildConfig
import com.activeprospect.trustedform.demo.di.DaggerApplicationComponent
import com.activeprospect.trustedform.demo.di.InjectorApplication
import com.activeprospect.trustedform.sdk.api.TrustedForm

class TrustedFormApplication : InjectorApplication() {

    override fun onCreate() {
        super.onCreate()

        TrustedForm.configure(
            appId = BuildConfig.APPLICATION_ID,
            accessToken = "e21e11c1743fb512d85b600887d2162bee3ced7b",
            application = this
        )

        initInjector()
    }

    private fun initInjector() {
        DaggerApplicationComponent.factory().application(this).also {
            initInjector(it)
            it.inject(this)
        }
    }
}