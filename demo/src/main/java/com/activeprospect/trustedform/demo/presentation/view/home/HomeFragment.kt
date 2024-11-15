package com.activeprospect.trustedform.demo.presentation.view.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.activeprospect.trustedform.demo.R
import com.activeprospect.trustedform.demo.databinding.FragmentHomeBinding
import com.activeprospect.trustedform.demo.Constants.ACTIVE_PROSPECT_WEBSITE
import com.activeprospect.trustedform.demo.common.commonview.BaseFragment
import com.activeprospect.trustedform.demo.data.preferences.ActiveProspectPreferences
import com.activeprospect.trustedform.demo.di.home.HomeInjector
import com.activeprospect.trustedform.demo.presentation.view.bottommenu.BottomMenuNavigator
import javax.inject.Inject

class HomeFragment(override val layoutId: Int = R.layout.fragment_home) :
    BaseFragment<FragmentHomeBinding>() {

    private val injector: HomeInjector
        get() = baseActivity.application as HomeInjector

    @Inject
    lateinit var navigator: BottomMenuNavigator

    @Inject
    lateinit var preferences: ActiveProspectPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferences.markIntroAsSeen()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() = with(binding) {
        buttonHomeCardDemo.setOnClickListener { navigator.navigateToComposeDemoForm() }
        cardHomeLearnMore.setOnClickListener { openWebBrowser() }
    }

    private fun openWebBrowser() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(ACTIVE_PROSPECT_WEBSITE))
        startActivity(browserIntent)
    }
}