package com.example.formapp.domain.model

data class User(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val gender: String?,
    val email: String,
    val password: String,
)
