package com.activeprospect.trustedform.demo.presentation.view.home

import android.content.Context
import android.os.Bundle
import android.view.View
import com.activeprospect.trustedform.R
import com.activeprospect.trustedform.databinding.FragmentHomeBinding
import com.activeprospect.trustedform.demo.common.commonview.BaseFragment
import com.activeprospect.trustedform.demo.di.home.HomeInjector
import com.activeprospect.trustedform.demo.presentation.view.bottommenu.BottomMenuNavigator
import javax.inject.Inject

class HomeFragment(override val layoutId: Int = R.layout.fragment_home) :
    BaseFragment<FragmentHomeBinding>() {

    private val injector: HomeInjector
        get() = baseActivity.application as HomeInjector

    @Inject
    lateinit var navigator: BottomMenuNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() = with(binding) {
        buttonHomeCardDemo.setOnClickListener { navigator.navigateToDemoForm() }
    }
}