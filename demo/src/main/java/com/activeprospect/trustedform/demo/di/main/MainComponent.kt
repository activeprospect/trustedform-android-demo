package com.activeprospect.trustedform.demo.di.main

import androidx.appcompat.app.AppCompatActivity
import com.activeprospect.trustedform.demo.di.ActivityScope
import com.activeprospect.trustedform.demo.di.bottommenu.BottomMenuComponent
import com.activeprospect.trustedform.demo.di.intoduction.IntroductionComponent
import com.activeprospect.trustedform.demo.presentation.view.main.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    fun inject(activity: MainActivity)

    fun getIntroductionComponentFactory(): IntroductionComponent.Factory
    fun getBottomMenuComponentFactory(): BottomMenuComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun bindActivity(@BindsInstance activity: AppCompatActivity): MainComponent
    }
}
