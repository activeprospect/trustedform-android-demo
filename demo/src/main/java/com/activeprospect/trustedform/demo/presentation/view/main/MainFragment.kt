package com.activeprospect.trustedform.demo.presentation.view.main

import android.content.Context
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.activeprospect.trustedform.demo.R
import com.activeprospect.trustedform.demo.databinding.FragmentMainBinding
import com.activeprospect.trustedform.demo.common.commonview.BaseFragment
import com.activeprospect.trustedform.demo.di.intoduction.IntroductionInjector
import javax.inject.Inject

class MainFragment(override val layoutId: Int = R.layout.fragment_main) :
    BaseFragment<FragmentMainBinding>() {

    private val injector: IntroductionInjector
        get() = baseActivity.application as IntroductionInjector

    @Inject
    lateinit var navigator: MainFragmentNavigator

    @Inject
    lateinit var mainNavigator: MainNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun setupActionBar() = with(binding) {
        baseActivity.apply {
            setSupportActionBar(toolbarIntroduction.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            toolbarIntroduction.toolbar.setOnClickListener { mainNavigator.navigateToBottomMenu() }
        }

        navContainerIntroduction.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                toolbarIntroduction.title = destination.label.toString()
                if (destination.id != R.id.splashFragment) {
                    toolbarIntroduction.toolbarAction.text = getString(R.string.skip)
                    toolbarIntroduction.toolbarAction.isVisible = true
                } else {
                    toolbarIntroduction.toolbarAction.isVisible = false
                }
            }
    }

    override fun onDestroy() {
        injector.clearIntroductionComponent()
        super.onDestroy()
    }
}