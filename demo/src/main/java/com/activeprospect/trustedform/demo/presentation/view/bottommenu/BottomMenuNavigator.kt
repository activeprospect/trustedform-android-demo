package com.activeprospect.trustedform.demo.presentation.view.bottommenu

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.activeprospect.trustedform.demo.R
import com.activeprospect.trustedform.demo.common.navigation.BaseFragmentNavigator
import javax.inject.Inject

interface BottomMenuNavigator {
    fun navigateToDemoFormCompleted()
    fun navigateToComposeDemoForm()
}

class BottomMenuNavigatorImpl @Inject constructor(
    private val fragment: BottomMenuFragment
) : BaseFragmentNavigator(fragment), BottomMenuNavigator {

    override val navController: NavController
        get() = fragment.binding.navContainerBottomMenu.findNavController()

    override fun navigateToDemoFormCompleted() {
        navigate(R.id.demoFormCompletedFragment)
    }

    override fun navigateToComposeDemoForm() {
        navigate(R.id.demoFormComposeFragment)
    }
}