package com.activeprospect.trustedform.demo.domain.models

data class ContactRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val message: String,
)
