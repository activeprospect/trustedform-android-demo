package com.activeprospect.trustedform.demo.presentation.view.demoform

import androidx.lifecycle.ViewModel
import com.activeprospect.trustedform.sdk.api.model.Certificate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class DemoFormViewModel @Inject constructor() : ViewModel() {

    // Certificate state management
    private val _certificate = MutableStateFlow<Certificate?>(null)
    val certificate: StateFlow<Certificate?> = _certificate.asStateFlow()

    fun setCertificate(certificate: Certificate) {
        _certificate.value = certificate
    }
}