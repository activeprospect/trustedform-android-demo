package com.activeprospect.trustedform.demo.presentation.view.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.activeprospect.trustedform.demo.Constants.SPLASH_SCREEN_DELAY
import com.activeprospect.trustedform.demo.data.preferences.ActiveProspectPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val preferences: ActiveProspectPreferences) : ViewModel() {

    private val _hasSeenIntroEvent = MutableLiveData<Boolean>()
    val hasSeenIntroEvent: LiveData<Boolean> = _hasSeenIntroEvent

    init {
        viewModelScope.launch {
            delay(SPLASH_SCREEN_DELAY)
            _hasSeenIntroEvent.value = preferences.hasSeenIntro()
        }
    }
}