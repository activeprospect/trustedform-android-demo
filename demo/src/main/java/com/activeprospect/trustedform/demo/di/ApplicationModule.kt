package com.activeprospect.trustedform.demo.di

import android.app.Application
import android.content.Context
import com.activeprospect.trustedform.sdk.api.TrustedForm
import com.activeprospect.trustedform.sdk.api.TrustedFormInterface
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ApplicationModule {

    @Binds
    fun bindAppContext(application: Application): Context

    companion object {
        @Provides
        fun provideTrustedForm(): TrustedFormInterface = TrustedForm
    }
}