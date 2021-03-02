package com.activeprospect.trustedform.demo.presentation.view.demoform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.activeprospect.trustedform.sdk.api.model.Certificate
import javax.inject.Inject

class DemoFormViewModel @Inject constructor() : ViewModel() {

    private val _validationStatus = MutableLiveData<Boolean>(false)
    val validationStatus: LiveData<Boolean> = _validationStatus

    var cert: Certificate? = null

    var fullName: String = ""
        set(value) {
            field = value
            validate()
        }
    var email: String = ""
        set(value) {
            field = value
            validate()
        }
    var phone: String = ""
        set(value) {
            field = value
            validate()
        }

    fun validate() {
        _validationStatus.postValue(email.isNotBlank() && fullName.isNotBlank() && phone.isNotBlank())
    }
}