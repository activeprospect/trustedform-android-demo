package com.activeprospect.trustedform.demo.presentation.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.activeprospect.trustedform.demo.Constants.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor() : ViewModel() {

    private val _navigateForward = MutableLiveData(false)
    val navigateForward: LiveData<Boolean> = _navigateForward

    init {
        viewModelScope.launch {
            delay(SPLASH_SCREEN_DELAY)
            _navigateForward.value = true
        }
    }
}