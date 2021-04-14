package com.activeprospect.trustedform.demo.domain.models

data class EmailRequest(
    val email: String,
    val firstName: String,
    val phone: String,
    val xxTrustedFormCertUrl: String
)
