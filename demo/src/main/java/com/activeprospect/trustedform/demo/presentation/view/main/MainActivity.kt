package com.activeprospect.trustedform.demo.presentation.view.main

import android.os.Bundle
import android.view.MotionEvent
import com.activeprospect.trustedform.R
import com.activeprospect.trustedform.databinding.ActivityMainBinding
import com.activeprospect.trustedform.demo.common.commonview.BaseActivity
import com.activeprospect.trustedform.demo.di.main.MainInjector
import com.activeprospect.trustedform.sdk.api.TrustedForm
import javax.inject.Inject

class MainActivity(override val layoutId: Int = R.layout.activity_main) :
    BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var navigator: MainNavigator

    private val injector: MainInjector
        get() = application as MainInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        TrustedForm.default.onTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {
        if (navigator.pop().not()) super.onBackPressed()
    }

    override fun onDestroy() {
        injector.clearMainComponent()
        super.onDestroy()
    }
}