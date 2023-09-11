package com.activeprospect.trustedform.demo

import com.activeprospect.trustedform.BuildConfig
import com.activeprospect.trustedform.demo.di.DaggerApplicationComponent
import com.activeprospect.trustedform.demo.di.InjectorApplication
import com.activeprospect.trustedform.sdk.api.TrustedForm
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class TrustedFormApplication : InjectorApplication() {

    override fun onCreate() {
        super.onCreate()

        TrustedForm.configure(
            appId = BuildConfig.APP_ID,
            apiUrl = BuildConfig.TRUSTED_FORM_URL,
            certUrl = BuildConfig.CERTIFICATE_URL,
            accessToken = BuildConfig.API_TOKEN,
            application = this
        )

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
            BuildConfig.APPCENTER_KEY,
            Analytics::class.java,
            Crashes::class.java
        )
    }
}