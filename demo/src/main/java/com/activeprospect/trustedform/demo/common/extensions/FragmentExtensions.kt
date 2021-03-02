package com.activeprospect.trustedform.demo.common.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.hideKeyboard() {
    val view = activity?.currentFocus
    val methodManager =
        activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    methodManager.hideSoftInputFromWindow(view?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun Fragment.setupBottomNavigationView(containerId: Int, view: BottomNavigationView) {
    childFragmentManager.findFragmentById(containerId)
        ?.findNavController()
        ?.let { view.setupWithNavController(it) }
}