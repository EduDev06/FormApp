package com.example.formapp.ui.form

import com.example.formapp.util.UiText

data class FormState(
    val firstName: String = "",
    val firstNameError: UiText? = null,
    val lastName: String = "",
    val lastNameError: UiText? = null,
    val phone: String = "",
    val phoneError: UiText? = null,
    val gender: String? = null,
    val genderError: UiText? = null,
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val passwordError: UiText? = null,
    val showPassword: Boolean = false,
    val confirmPassword: String = "",
    val confirmPasswordError: UiText? = null,
    val showConfirmPassword: Boolean = false
)
