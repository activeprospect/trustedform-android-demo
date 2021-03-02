package com.activeprospect.trustedform.demo.presentation.view.main

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.activeprospect.trustedform.R
import com.activeprospect.trustedform.demo.common.navigation.BaseFragmentNavigator
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

interface MainFragmentNavigator {
    fun navigateToWhoIs()
    fun navigateToWhatIs()
    fun navigateToSignIn()
}

class MainFragmentNavigatorImpl @Inject constructor(
    private val fragment: MainFragment
) : BaseFragmentNavigator(fragment), MainFragmentNavigator {

    override val navController: NavController
        get() = fragment.navContainerIntroduction.findNavController()

    override fun navigateToWhoIs() = navigate(R.id.whoIsFragment)

    override fun navigateToWhatIs() = navigate(R.id.whatIsFragment)

    override fun navigateToSignIn() = navigate(R.id.signInFragment)
}