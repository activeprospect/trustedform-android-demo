package com.activeprospect.trustedform.demo.presentation.view.main

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.activeprospect.trustedform.R
import com.activeprospect.trustedform.demo.common.navigation.BaseActivityNavigator
import javax.inject.Inject

interface MainNavigator {

    fun navigateToIntroduction()
    fun navigateToBottomMenu()

    fun pop(): Boolean
}

class MainNavigatorImpl @Inject constructor(
    private val activity: AppCompatActivity
) : BaseActivityNavigator(), MainNavigator {

    override val navController: NavController
        get() = activity.findNavController(R.id.navContainerMain)

    override fun navigateToIntroduction() {
        navigate(R.id.introductionFragment)
    }

    override fun navigateToBottomMenu() {
        navigate(R.id.action_introductionFragment_to_bottomMenuFragment)
    }

    override fun pop() = popBackStack()
}