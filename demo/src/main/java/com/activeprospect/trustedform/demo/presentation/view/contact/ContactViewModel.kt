package com.activeprospect.trustedform.demo.presentation.view.contact

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ContactViewModel @Inject constructor() : ViewModel() {

    var firstName: String = ""
    var lastName: String = ""
    var workEmail: String = ""
    var phoneNumber: String = ""
    var message: String = ""
}