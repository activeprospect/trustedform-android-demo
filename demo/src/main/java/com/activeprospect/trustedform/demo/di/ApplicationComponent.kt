package com.activeprospect.trustedform.demo.di

import android.app.Application
import com.activeprospect.trustedform.demo.TrustedFormApplication
import com.activeprospect.trustedform.demo.di.main.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun getMainComponentFactory(): MainComponent.Factory

    fun inject(application: TrustedFormApplication)

    @Component.Factory
    interface Factory {
        fun application(@BindsInstance app: Application): ApplicationComponent
    }
}