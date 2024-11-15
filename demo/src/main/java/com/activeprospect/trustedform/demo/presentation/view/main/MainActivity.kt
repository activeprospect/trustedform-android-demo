package com.activeprospect.trustedform.demo.presentation.view.main

import android.os.Bundle
import com.activeprospect.trustedform.demo.R
import com.activeprospect.trustedform.demo.databinding.ActivityMainBinding
import com.activeprospect.trustedform.demo.common.commonview.BaseActivity
import com.activeprospect.trustedform.demo.di.main.MainInjector
import javax.inject.Inject

class MainActivity(override val layoutId: Int = R.layout.activity_main) :
    BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var navigator: MainNavigator

    private val injector: MainInjector
        get() = application as MainInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        if (navigator.pop().not()) super.onBackPressed()
    }

    override fun onDestroy() {
        injector.clearMainComponent()
        super.onDestroy()
    }
}