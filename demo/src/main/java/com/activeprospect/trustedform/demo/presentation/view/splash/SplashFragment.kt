package com.activeprospect.trustedform.demo.presentation.view.splash

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.activeprospect.trustedform.demo.R
import com.activeprospect.trustedform.demo.databinding.FragmentSplashBinding
import com.activeprospect.trustedform.demo.common.commonview.BaseFragment
import com.activeprospect.trustedform.demo.common.viewmodels.ViewModelFactory
import com.activeprospect.trustedform.demo.di.splash.SplashInjector
import com.activeprospect.trustedform.demo.presentation.view.main.MainFragmentNavigator
import com.activeprospect.trustedform.demo.presentation.view.main.MainNavigator
import javax.inject.Inject

class SplashFragment(override val layoutId: Int = R.layout.fragment_splash) :
    BaseFragment<FragmentSplashBinding>() {

    private val injector: SplashInjector
        get() = baseActivity.application as SplashInjector

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigator: MainFragmentNavigator

    @Inject
    lateinit var mainNavigator: MainNavigator

    private val viewModel: SplashViewModel by viewModels { viewModelFactory }

    private val observer = Observer<Boolean> { hasSeenIntro ->
        if (hasSeenIntro) {
            mainNavigator.navigateToBottomMenu()
        } else {
            navigator.navigateToWhoIs()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.hasSeenIntroEvent.observe(viewLifecycleOwner, observer)
    }

    override fun setupActionBar() = Unit
}