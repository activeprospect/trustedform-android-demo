package com.activeprospect.trustedform.demo.presentation.view.whois

import android.content.Context
import android.os.Bundle
import android.view.View
import com.activeprospect.trustedform.R
import com.activeprospect.trustedform.databinding.FragmentWhoIsBinding
import com.activeprospect.trustedform.demo.common.commonview.BaseFragment
import com.activeprospect.trustedform.demo.di.whois.WhoIsInjector
import com.activeprospect.trustedform.demo.presentation.view.main.MainFragmentNavigator
import javax.inject.Inject

class WhoIsFragment(override val layoutId: Int = R.layout.fragment_who_is) :
    BaseFragment<FragmentWhoIsBinding>() {

    @Inject
    lateinit var navigator: MainFragmentNavigator

    private val injector: WhoIsInjector
        get() = baseActivity.application as WhoIsInjector

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonWhoIsContinue.setOnClickListener { navigator.navigateToWhatIs() }
    }

    override fun setupActionBar() = Unit
}