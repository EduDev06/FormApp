package com.example.formapp.ui.form

sealed class FormEvent {
    data class EnteredFirstName(val value: String): FormEvent()
    data class EnteredLastName(val value: String): FormEvent()
    data class EnteredPhone(val value: String): FormEvent()
    data class ChoseGender(val value: String): FormEvent()
    data class EnteredEmail(val value: String): FormEvent()
    data class EnteredPassword(val value: String): FormEvent()
    data class ChangePasswordVisibility(val value: Boolean): FormEvent()
    data class EnteredConfirmPassword(val value: String): FormEvent()
    data class ChangeConfirmPasswordVisibility(val value: Boolean): FormEvent()
    object InsertUser: FormEvent()
}
