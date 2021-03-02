package com.activeprospect.trustedform.demo.presentation.view.bottommenu

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.activeprospect.trustedform.R
import com.activeprospect.trustedform.demo.common.navigation.BaseFragmentNavigator
import kotlinx.android.synthetic.main.fragment_bottom_menu.*
import javax.inject.Inject

interface BottomMenuNavigator {
    fun navigateToDemoFormCompleted()
    fun navigateToDemoForm()
}

class BottomMenuNavigatorImpl @Inject constructor(
    private val fragment: BottomMenuFragment
) : BaseFragmentNavigator(fragment), BottomMenuNavigator {

    override val navController: NavController
        get() = fragment.navContainerBottomMenu.findNavController()

    override fun navigateToDemoFormCompleted() {
        navigate(R.id.demoFormCompletedFragment)
    }

    override fun navigateToDemoForm() {
        navigate(R.id.demoFormFragment)
    }
}