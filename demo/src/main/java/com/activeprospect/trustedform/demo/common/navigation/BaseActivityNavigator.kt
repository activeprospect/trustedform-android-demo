package com.activeprospect.trustedform.demo.common.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections

abstract class BaseActivityNavigator {

    abstract val navController: NavController

    fun navigate(resId: Int) = navController.navigate(resId)

    fun navigate(resId: Int, bundle: Bundle) = navController.navigate(resId, bundle)

    fun navigate(directions: NavDirections) = navController.navigate(directions)

    fun popBackStack() = navController.popBackStack()

    fun popBackStack(destination: Int, inclusive: Boolean) =
        navController.popBackStack(destination, inclusive)
}