package com.activeprospect.trustedform.demo

import com.activeprospect.trustedform.BuildConfig
import com.activeprospect.trustedform.demo.di.DaggerApplicationComponent
import com.activeprospect.trustedform.demo.di.InjectorApplication
import com.activeprospect.trustedform.sdk.api.TrustedForm
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import javax.inject.Inject

class TrustedFormApplication : InjectorApplication() {

    override fun onCreate() {
        super.onCreate()

        initInjector()
        setupAppCenter()
    }

    private fun initInjector() {
        DaggerApplicationComponent.factory().application(this).also {
            initInjector(it)
            it.inject(this)
        }
    }

    private fun setupAppCenter() {
        AppCenter.start(
            this,
<<<<<<< HEAD
            "",
=======
            BuildConfig.APPCENTER_KEY,
>>>>>>> 663ba28 (0.1.0alpha)
            Analytics::class.java,
            Crashes::class.java
        )
    }
}