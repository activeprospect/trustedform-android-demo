package com.activeprospect.trustedform.demo.presentation.view.bottommenu

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.activeprospect.trustedform.demo.R
import com.activeprospect.trustedform.demo.common.commonview.BaseFragment
import com.activeprospect.trustedform.demo.common.extensions.setupBottomNavigationView
import com.activeprospect.trustedform.demo.databinding.FragmentBottomMenuBinding
import com.activeprospect.trustedform.demo.di.bottommenu.BottomMenuInjector
import javax.inject.Inject

class BottomMenuFragment(override val layoutId: Int = R.layout.fragment_bottom_menu) :
    BaseFragment<FragmentBottomMenuBinding>() {

    @Inject
    lateinit var navigator: BottomMenuNavigator

    private val injector: BottomMenuInjector
        get() = baseActivity.application as BottomMenuInjector

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() = with(binding) {
        setupBottomNavigationView(R.id.navContainerBottomMenu, bottomNavigationBar)
        blockBottomBarReselection()
    }

    override fun setupActionBar() = with(binding) {
        baseActivity.apply {
            setSupportActionBar(toolbarBottomMenu.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        navContainerBottomMenu.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                toolbarBottomMenu.title = destination.label.toString()
            }
    }

    private fun blockBottomBarReselection() {
        binding.bottomNavigationBar.setOnNavigationItemReselectedListener { }
    }

    override fun onDestroy() {
        injector.clearBottomMenuComponent()
        super.onDestroy()
    }
}