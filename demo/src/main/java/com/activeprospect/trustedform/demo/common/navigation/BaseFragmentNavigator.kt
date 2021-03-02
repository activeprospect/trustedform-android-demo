package com.activeprospect.trustedform.demo.common.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

open class BaseFragmentNavigator(private val fragment: Fragment) {

    open val navController: NavController
        get() = fragment.findNavController()

    fun navigate(resId: Int) = navController.navigate(resId)

    fun navigate(resId: Int, bundle: Bundle) = navController.navigate(resId, bundle)

    fun navigate(directions: NavDirections) = navController.navigate(directions)

    fun popBackStack() = navController.popBackStack()

    fun navigateUp() = navController.navigateUp()

    fun popBackStack(destination: Int, inclusive: Boolean): Boolean {
        return navController.popBackStack(destination, inclusive)
    }
}